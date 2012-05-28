package com.vanaltj.canweather.envcan.xml.sitedata;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root
public class Name {

    @Attribute(name="code")
    private String code;

    @Attribute(name="lat")
    private String lat;

    @Attribute(name="lon")
    private String lon;

    @Text
    private String name;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getLatitude() {
        return lat;
    }

    public String getLongitude() {
        return lon;
    }
}
