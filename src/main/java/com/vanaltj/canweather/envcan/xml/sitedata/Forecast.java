package com.vanaltj.canweather.envcan.xml.sitedata;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(strict=false)
public class Forecast {

    @Element(name="period")
    private Period period;

    @Element(name="textSummary")
    private String textSummary;

    @Element(name="abbreviatedForecast")
    private AbbreviatedForecast abbreviatedForecast;

    @Element(name="temperatures")
    private Temperatures temperatures;

    public Period getPeriod() {
        return period;
    }

    public String getTextSummary() {
        return textSummary;
    }

    public Temperature getHigh() {
        return temperatures.getHigh();
    }

    public Temperature getLow() {
        return temperatures.getLow();
    }

    public String getBasicForecast() {
        return abbreviatedForecast.getTextSummary();
    }
}
