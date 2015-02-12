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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.02.11 at 11:13:45 PM EST 
//


package org.oasis_open.docs.s_ramp.ns.s_ramp_v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Part complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Part">
 *   &lt;complexContent>
 *     &lt;extension base="{http://docs.oasis-open.org/s-ramp/ns/s-ramp-v1.0}NamedWsdlDerivedArtifactType">
 *       &lt;sequence>
 *         &lt;element name="type" type="{http://docs.oasis-open.org/s-ramp/ns/s-ramp-v1.0}xsdTypeTarget" minOccurs="0"/>
 *         &lt;element name="element" type="{http://docs.oasis-open.org/s-ramp/ns/s-ramp-v1.0}elementDeclarationTarget" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Part", propOrder = {
    "type",
    "element"
})
public class Part
    extends NamedWsdlDerivedArtifactType
    implements Serializable
{

    private static final long serialVersionUID = -6759004356658533560L;
    protected XsdTypeTarget type;
    protected ElementDeclarationTarget element;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link XsdTypeTarget }
     *     
     */
    public XsdTypeTarget getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link XsdTypeTarget }
     *     
     */
    public void setType(XsdTypeTarget value) {
        this.type = value;
    }

    /**
     * Gets the value of the element property.
     * 
     * @return
     *     possible object is
     *     {@link ElementDeclarationTarget }
     *     
     */
    public ElementDeclarationTarget getElement() {
        return element;
    }

    /**
     * Sets the value of the element property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElementDeclarationTarget }
     *     
     */
    public void setElement(ElementDeclarationTarget value) {
        this.element = value;
    }

}
