/*
 * Copyright 2014 JBoss Inc
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
package org.artificer.repository.test;

import org.artificer.common.ArtifactContent;
import org.artificer.common.ArtifactType;
import org.artificer.common.ArtificerConstants;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.UrlAsset;
import org.jboss.shrinkwrap.impl.base.exporter.zip.ZipExporterImpl;
import org.junit.Test;
import org.oasis_open.docs.s_ramp.ns.s_ramp_v1.BaseArtifactType;

import java.io.InputStream;
import java.net.URL;

/**
 * @author Brett Meyer.
 */
public class ArchiveExpansionTest extends AbstractNoAuditingPersistenceTest {

    @Test
    public void testArchiveExpansion() throws Exception {
        // build an archive with shrinkwrap
        GenericArchive archive = ShrinkWrap.create(GenericArchive.class, "test.zip");
        URL xsdUrl = this.getClass().getResource("/sample-files/wsdl/jcr-sample-externalrefs.xsd");
        URL wsdlUrl = this.getClass().getResource("/sample-files/wsdl/jcr-sample-externalrefs.wsdl");
        archive.add(new UrlAsset(xsdUrl), "foo/path");
        archive.add(new UrlAsset(wsdlUrl), "foo/path");
        InputStream is = new ZipExporterImpl(archive).exportAsInputStream();

        // persist everything, emulating what ArtifactServiceImpl would do

        BaseArtifactType archiveArtifact = ArtifactType.ExtendedArtifactType("FooArchive").newArtifactInstance();
        archiveArtifact = persistenceManager.persistArtifact(archiveArtifact, new ArtifactContent("test.zip", is));

        BaseArtifactType xsdArtifact = ArtifactType.XsdDocument().newArtifactInstance();
        xsdArtifact.getOtherAttributes().put(
                ArtificerConstants.ARTIFICER_EXPANDED_FROM_ARCHIVE_UUID_QNAME, archiveArtifact.getUuid());
        xsdArtifact.getOtherAttributes().put(
                ArtificerConstants.ARTIFICER_EXPANDED_FROM_ARCHIVE_PATH_QNAME, "foo/path/jcr-sample-externalrefs.xsd");
        xsdArtifact = persistenceManager.persistArtifact(xsdArtifact,
                new ArtifactContent("foo/path/jcr-sample-externalrefs.xsd", xsdUrl.openStream()));

        BaseArtifactType wsdlArtifact = ArtifactType.WsdlDocument().newArtifactInstance();
        wsdlArtifact.getOtherAttributes().put(
                ArtificerConstants.ARTIFICER_EXPANDED_FROM_ARCHIVE_UUID_QNAME, archiveArtifact.getUuid());
        wsdlArtifact.getOtherAttributes().put(
                ArtificerConstants.ARTIFICER_EXPANDED_FROM_ARCHIVE_PATH_QNAME, "foo/path/jcr-sample-externalrefs.wsdl");
        wsdlArtifact = persistenceManager.persistArtifact(wsdlArtifact,
                new ArtifactContent("foo/path/jcr-sample-externalrefs.wsdl", wsdlUrl.openStream()));

        // TODO: search all
        // TODO: /s-ramp[@derived = 'false' and xp2:not(expandedFromArchive)]
        // TODO: /s-ramp[@derived = 'false' and expandedFromArchive]
        // TODO: /s-ramp[@derived = 'true']
    }
}
