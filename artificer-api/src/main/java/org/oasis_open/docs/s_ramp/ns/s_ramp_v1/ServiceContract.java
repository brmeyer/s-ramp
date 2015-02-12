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
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServiceContract complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceContract">
 *   &lt;complexContent>
 *     &lt;extension base="{http://docs.oasis-open.org/s-ramp/ns/s-ramp-v1.0}PolicySubject">
 *       &lt;sequence>
 *         &lt;element name="involvesParty" type="{http://docs.oasis-open.org/s-ramp/ns/s-ramp-v1.0}actorTarget" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="specifies" type="{http://docs.oasis-open.org/s-ramp/ns/s-ramp-v1.0}effectTarget" maxOccurs="unbounded"/>
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
@XmlType(name = "ServiceContract", propOrder = {
    "involvesParty",
    "specifies"
})
public class ServiceContract
    extends PolicySubject
    implements Serializable
{

    private static final long serialVersionUID = 1709443742632392656L;
    protected List<ActorTarget> involvesParty;
    @XmlElement(required = true)
    protected List<EffectTarget> specifies;

    /**
     * Gets the value of the involvesParty property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the involvesParty property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvolvesParty().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActorTarget }
     * 
     * 
     */
    public List<ActorTarget> getInvolvesParty() {
        if (involvesParty == null) {
            involvesParty = new ArrayList<ActorTarget>();
        }
        return this.involvesParty;
    }

    /**
     * Gets the value of the specifies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specifies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecifies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EffectTarget }
     * 
     * 
     */
    public List<EffectTarget> getSpecifies() {
        if (specifies == null) {
            specifies = new ArrayList<EffectTarget>();
        }
        return this.specifies;
    }

}
