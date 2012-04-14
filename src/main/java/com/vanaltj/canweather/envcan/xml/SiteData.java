package com.vanaltj.canweather.envcan.xml;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class SiteData {

    private Calendar time = null;

    @Element(name="license")
    private String license;

    @ElementList(inline=true)
    private List<DateTime> times;

    @Element(name="")
    private Location location;

    public URL getLicenseURL() throws MalformedURLException {
        return new URL(license);
    }

    public Calendar getTime() {
        if (time == null) {
            cacheTime();
        }
        return (Calendar) time.clone();
    }

    private void cacheTime() {
        for (DateTime dt : times) {
            Calendar cal = dt.getDate();
            if (cal.get(Calendar.ZONE_OFFSET) == 0) {
                time = cal;
            }
        }
    }

    public Location getLocation() {
        return location;
    }
}
