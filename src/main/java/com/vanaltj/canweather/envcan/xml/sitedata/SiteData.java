package com.vanaltj.canweather.envcan.xml.sitedata;

import static com.vanaltj.canweather.envcan.xml.XmlUtils.selectUtcTime;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.vanaltj.canweather.envcan.xml.DateTime;

@Root(strict=false)
public class SiteData {

    private Calendar time = null;

    @Element(name="license")
    private String license;

    @ElementList(inline=true)
    private List<DateTime> times;

    @Element(name="location")
    private Location location;

    @Element(name="currentConditions")
    private CurrentConditions conditions;

    @Element(name="forecastGroup")
    private ForecastGroup forecastGroup;

    public SiteData(@Element(name="license") String license,
            @ElementList(inline=true) List<DateTime> times,
            @Element(name="location") Location location,
            @Element(name="currentConditions") CurrentConditions conditions,
            @Element(name="forecastGroup") ForecastGroup forecastGroup) {
        this.license = license;
        this.times = times;
        this.location = location;
        this.conditions = conditions;
        this.forecastGroup = forecastGroup;

        this.time = (Calendar) selectUtcTime(this.times);
    }

    public URL getLicenseURL() throws MalformedURLException {
        return new URL(license);
    }

    public Calendar getTime() {
        return (Calendar) time.clone();
    }

    public Location getLocation() {
        return location;
    }

    public CurrentConditions getCurrentConditions() {
        return conditions;
    }

    public ForecastGroup getForecastGroup() {
        return forecastGroup;
    }
}
