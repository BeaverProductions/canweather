package com.vanaltj.canweather.envcan.xml.sitedata;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class AbbreviatedForecast {

    @Element(name="pop", required=false)
    private float pop;

    @Element(name="textSummary", required=false)
    private String textSummary;

    public String getTextSummary() {
        if (textSummary == null) {
            textSummary = "";
        }
        return textSummary;
    }
}
