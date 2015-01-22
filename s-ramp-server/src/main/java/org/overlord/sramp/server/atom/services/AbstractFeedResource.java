/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.overlord.sramp.server.atom.services;

import org.jboss.resteasy.plugins.providers.atom.Entry;
import org.jboss.resteasy.plugins.providers.atom.Feed;
import org.jboss.resteasy.plugins.providers.atom.Link;
import org.jboss.resteasy.plugins.providers.atom.Person;
import org.oasis_open.docs.s_ramp.ns.s_ramp_v1.BaseArtifactType;
import org.overlord.sramp.atom.MediaType;
import org.overlord.sramp.atom.err.SrampAtomException;
import org.overlord.sramp.atom.visitors.ArtifactToSummaryAtomEntryVisitor;
import org.overlord.sramp.common.SrampConstants;
import org.overlord.sramp.common.visitors.ArtifactVisitorHelper;
import org.overlord.sramp.repository.query.ArtifactSet;
import org.overlord.sramp.server.QueryServiceImpl;
import org.overlord.sramp.server.i18n.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;


/**
 * Base class for all resources that respond with Atom Feeds.
 */
public abstract class AbstractFeedResource extends AbstractResource {

	private static Logger logger = LoggerFactory.getLogger(AbstractFeedResource.class);

	protected final QueryServiceImpl queryService = new QueryServiceImpl();

	/**
	 * Common method that performs a query for artifacts and returns them in an Atom {@link Feed}.
	 * @param query the x-path formatted s-ramp query
	 * @param startIndex which index within the result set to start with (0 indexed)
	 * @param count the number of items desired
	 * @param orderBy the property to sort the results by
	 * @param ascending the sort direction
	 * @param propNames the set of s-ramp property names - the extra properties that the query should return as part of the {@link Feed}
	 * @return an Atom {@link Feed}
	 * @throws SrampAtomException
	 */
	protected Feed createArtifactFeed(String query, Integer startPage, Integer startIndex, Integer count, String orderBy,
			Boolean ascending, Set<String> propNames, String baseUrl) throws SrampAtomException {
	    if (query == null)
            throw new SrampAtomException(Messages.i18n.format("MISSING_QUERY_PARAM")); //$NON-NLS-1$

		try {
            ArtifactSet artifactSet = queryService.query(query, orderBy, ascending);
            long totalSize = artifactSet.size();
            List<BaseArtifactType> artifacts = queryService.pagedList(artifactSet, startPage, startIndex, count);
            startIndex = queryService.startIndex(startPage, startIndex, count);
            Feed feed = createFeed(artifacts, startIndex, totalSize, propNames, baseUrl);
			addPaginationLinks(feed, startIndex, artifacts.size(), totalSize, query, orderBy, ascending, baseUrl);
			return feed;
		} catch (Throwable e) {
			logError(logger, Messages.i18n.format("Error trying to create an Artifact Feed."), e); //$NON-NLS-1$
			throw new SrampAtomException(e);
		}
	}

	/**
	 * Creates the Atom {@link Feed} from the given artifact set (query result set).
	 *
	 * Note: the Atom feed format allows pagination via the following links:
	 *
	 * <pre>
	 *   <link rel="first" href="http://www.example.org/feed"/>
	 *   <link rel="next" href="http://www.example.org/feed?page=4"/>
	 *   <link rel="previous" href="http://www.example.org/feed?page=2"/>
	 *   <link rel="last" href="http://www.example.org/feed?page=147"/>
	 * </pre>
	 *
     * @param artifacts
     * @param startIndex
     * @param totalSize
	 * @param propNames the additional s-ramp properties to return in the {@link Feed}
	 * @return an Atom {@link Feed}
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
    private Feed createFeed(List<BaseArtifactType> artifacts, int startIndex, long totalSize, Set<String> propNames, String baseUrl) throws Exception {
		Feed feed = new Feed();
		feed.getExtensionAttributes().put(SrampConstants.SRAMP_PROVIDER_QNAME, "JBoss Overlord"); //$NON-NLS-1$
        feed.getExtensionAttributes().put(SrampConstants.SRAMP_ITEMS_PER_PAGE_QNAME, String.valueOf(artifacts.size()));
        feed.getExtensionAttributes().put(SrampConstants.SRAMP_START_INDEX_QNAME, String.valueOf(startIndex));
        feed.getExtensionAttributes().put(SrampConstants.SRAMP_TOTAL_RESULTS_QNAME, String.valueOf(totalSize));
		feed.setId(new URI("urn:uuid:" + UUID.randomUUID().toString())); //$NON-NLS-1$
		feed.setTitle("S-RAMP Feed"); //$NON-NLS-1$
		feed.setSubtitle("Ad Hoc query feed"); //$NON-NLS-1$
		feed.setUpdated(new Date());
		feed.getAuthors().add(new Person("anonymous")); //$NON-NLS-1$

		ArtifactToSummaryAtomEntryVisitor visitor = new ArtifactToSummaryAtomEntryVisitor(baseUrl, propNames);
       for (BaseArtifactType artifact : artifacts) {
			ArtifactVisitorHelper.visitArtifact(visitor, artifact);
			Entry entry = visitor.getAtomEntry();
			feed.getEntries().add(entry);
			visitor.reset();
		}

		return feed;
	}

	/**
	 * Add pagination links to the feed.
	 *
	 * @param feed
     * @param startIndex
     * @param totalSize
	 * @param query
	 * @param orderBy
	 * @param ascending
	 * @param baseUrl
	 * @throws UnsupportedEncodingException
	 */
	private void addPaginationLinks(Feed feed, int startIndex, int pageSize, long totalSize, String query,
			String orderBy, Boolean ascending, String baseUrl) throws UnsupportedEncodingException {
        String hrefPattern = "%1$s?query=%2$s&page=%3$s&pageSize=%4$s&orderBy=%5$s&ascending=%6$s"; //$NON-NLS-1$
		String encodedQuery = URLEncoder.encode(query, "UTF-8"); //$NON-NLS-1$
		String firstHref = String.format(hrefPattern, baseUrl, encodedQuery, 0, String.valueOf(pageSize),
				String.valueOf(orderBy), String.valueOf(ascending));
		int prevIndex = Math.max(0,  startIndex - pageSize);
		String prevHref = String.format(hrefPattern, baseUrl, encodedQuery, prevIndex, String.valueOf(pageSize),
				String.valueOf(orderBy), String.valueOf(ascending));
		String nextHref = String.format(hrefPattern, baseUrl, encodedQuery, startIndex + pageSize, String.valueOf(pageSize),
				String.valueOf(orderBy), String.valueOf(ascending));

		Link first = new Link("first", firstHref, MediaType.APPLICATION_ATOM_XML_FEED_TYPE); //$NON-NLS-1$
		Link prev = new Link("prev", prevHref, MediaType.APPLICATION_ATOM_XML_FEED_TYPE); //$NON-NLS-1$
		Link next = new Link("next", nextHref, MediaType.APPLICATION_ATOM_XML_FEED_TYPE); //$NON-NLS-1$

		if (startIndex > 0) {
			feed.getLinks().add(first);
			feed.getLinks().add(prev);
		}
		if (startIndex + pageSize >= totalSize) {
			feed.getLinks().add(next);
		}

	}

}
