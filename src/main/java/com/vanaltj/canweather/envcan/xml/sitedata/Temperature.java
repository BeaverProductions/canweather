/*
 * Copyright 2012 Jon VanAlten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vanaltj.canweather.envcan.xml.sitedata;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Temperature {

    public enum TemperatureClass {
        HIGH("high"),
        LOW("low"),
        CURRENT("current");

        private String value;
        private TemperatureClass(String value) {
            this.value = value;
        }

        public static TemperatureClass fromString(String string) {
            for (TemperatureClass clazz : TemperatureClass.values()) {
                if (clazz.getValue().equals(string)) {
                    return clazz;
                }
            }
            throw new IllegalArgumentException("Input \"" + string + "\" is not a valid TemperatureClass.");
        }

        public String getValue() {
            return value;
        }
    }

    @Attribute(name="unitType")
    private String unitType;

    @Attribute(name="units")
    private String units;

    @Attribute(name="class", required=false)
    private String klass;
    private TemperatureClass theClass;

    @Text(required=false)
    private float temperature;

    public String getUnitType() {
        return unitType;
    }

    public String getUnits() {
        return units;
    }

    public TemperatureClass getTemperatureClass() {
        cacheTemperatureClass();
        return theClass;
    }

    private void cacheTemperatureClass() {
        if (theClass == null) {
            if (klass == null) {
                klass = "current";
            }
            theClass = TemperatureClass.fromString(klass);
        }
    }

    public float getValue() {
        return temperature;
    }
}
