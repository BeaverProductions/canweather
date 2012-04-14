package com.vanaltj.canweather.envcan;

import java.net.URL;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.envcan.xml.Site;
import com.vanaltj.canweather.envcan.xml.SiteList;

public class XMLWrapper {

    public class XMLCreationException extends RuntimeException {
        private static final long serialVersionUID = -1773659271289392073L;

        public XMLCreationException(String message) {
            super(message);
        }
        
    }

    private SiteList sites;

    private static final String SITES_URL = "http://dd.weatheroffice.ec.gc.ca/citypage_weather/xml/siteList.xml";
    private List<Place> places;

    public XMLWrapper() {
        Serializer serializer = new Persister();
        URL siteURL;
        try {
            siteURL = new URL(SITES_URL);
            sites = serializer.read(SiteList.class, siteURL.openStream());
        } catch (Exception ex) {
            throw new XMLCreationException("");
        }
        makePlaces();
    }

    public List<Place> getPlaces() {
        return places;
    }

    private void makePlaces() {
        for (String province : sites.getProvinceCodes()) {
            List<Site> someSites = sites.getSitesByProvince(province);
            for (Site site : someSites) {
            }
        }
    }
}
