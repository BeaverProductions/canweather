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
