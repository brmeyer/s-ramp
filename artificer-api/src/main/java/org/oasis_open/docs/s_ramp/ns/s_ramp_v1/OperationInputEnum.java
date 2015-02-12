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

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for operationInputEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="operationInputEnum">
 *   &lt;restriction base="{http://docs.oasis-open.org/s-ramp/ns/s-ramp-v1.0}derivedArtifactEnum">
 *     &lt;enumeration value="OperationInput"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "operationInputEnum")
@XmlEnum(DerivedArtifactEnum.class)
public enum OperationInputEnum {

    @XmlEnumValue("OperationInput")
    OPERATION_INPUT(DerivedArtifactEnum.OPERATION_INPUT);
    private final DerivedArtifactEnum value;

    OperationInputEnum(DerivedArtifactEnum v) {
        value = v;
    }

    public DerivedArtifactEnum value() {
        return value;
    }

    public static OperationInputEnum fromValue(DerivedArtifactEnum v) {
        for (OperationInputEnum c: OperationInputEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }

}
