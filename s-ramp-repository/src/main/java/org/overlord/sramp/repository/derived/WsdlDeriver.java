/*
 * Copyright 2012 JBoss Inc
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
package org.overlord.sramp.repository.derived;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;

import org.s_ramp.xmlns._2010.s_ramp.BaseArtifactType;
import org.s_ramp.xmlns._2010.s_ramp.DerivedArtifactType;

/**
 * Creates derived content from a WSDL document.  This will create the derived content as
 * defined in the WSDL model found in the s-ramp specification.  The following derived
 * artifact types will (potentially) be created:
 *
 * <ul>
 *   <li>WsdlService</li>
 *   <li>Port</li>
 *   <li>WsdlExtension</li>
 *   <li>Part</li>
 *   <li>Message</li>
 *   <li>Fault</li>
 *   <li>PortType</li>
 *   <li>Operation</li>
 *   <li>OperationInput</li>
 *   <li>OperationOutput</li>
 *   <li>Binding</li>
 *   <li>BindingOperation</li>
 *   <li>BindingOperationInput</li>
 *   <li>BindingOperationOutput</li>
 *   <li>BindingOperationFault</li>
 * </ul>
 *
 * @author eric.wittmann@redhat.com
 */
public class WsdlDeriver implements ArtifactDeriver {

	/**
	 * Constructor.
	 */
	public WsdlDeriver() {
	}

	/**
	 * @see org.overlord.sramp.repository.derived.ArtifactDeriver#derive(org.s_ramp.xmlns._2010.s_ramp.BaseArtifactType, java.io.InputStream)
	 */
	@Override
	public Collection<DerivedArtifactType> derive(BaseArtifactType artifact, InputStream content) throws IOException {
		Collection<DerivedArtifactType> derivedArtifacts = new LinkedList<DerivedArtifactType>();
		return derivedArtifacts;
	}

}