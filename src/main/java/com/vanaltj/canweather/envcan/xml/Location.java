package com.vanaltj.canweather.envcan.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class Location {

    @Element(name="province")
    private String province;

    @Element(name="name")
    private Name name;
}
