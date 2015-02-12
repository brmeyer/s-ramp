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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SoapAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SoapAddress">
 *   &lt;complexContent>
 *     &lt;extension base="{http://docs.oasis-open.org/s-ramp/ns/s-ramp-v1.0}WsdlExtension">
 *       &lt;attribute name="soapLocation" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;anyAttribute/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SoapAddress")
public class SoapAddress
    extends WsdlExtension
    implements Serializable
{

    private static final long serialVersionUID = 1485326076659251194L;
    @XmlAttribute(name = "soapLocation")
    @XmlSchemaType(name = "anyURI")
    protected String soapLocation;

    /**
     * Gets the value of the soapLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoapLocation() {
        return soapLocation;
    }

    /**
     * Sets the value of the soapLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoapLocation(String value) {
        this.soapLocation = value;
    }

}
