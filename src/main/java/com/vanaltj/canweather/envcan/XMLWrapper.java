package com.vanaltj.canweather.envcan;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;

import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.data.WeatherData;
import com.vanaltj.canweather.data.location.Coordinates;
import com.vanaltj.canweather.envcan.data.location.EnvCanLatitude;
import com.vanaltj.canweather.envcan.data.location.EnvCanLongitude;
import com.vanaltj.canweather.envcan.xml.sitedata.CurrentConditions;
import com.vanaltj.canweather.envcan.xml.sitedata.Forecast;
import com.vanaltj.canweather.envcan.xml.sitedata.ForecastGroup;
import com.vanaltj.canweather.envcan.xml.sitedata.Name;
import com.vanaltj.canweather.envcan.xml.sitedata.SiteData;
import com.vanaltj.canweather.envcan.xml.sitelist.Site;
import com.vanaltj.canweather.envcan.xml.sitelist.SiteList;

public class XMLWrapper {

    public class XMLCreationException extends RuntimeException {
        private static final long serialVersionUID = -1773659271289392073L;

        public XMLCreationException(String message) {
            super(message);
        }

        public XMLCreationException(String string, Exception cause) {
            super(string, cause);
        }
        
    }

    private SiteList sites;

    private static final String XML_URL_BASE = "http://dd.weatheroffice.ec.gc.ca/citypage_weather/xml/";
    private static final String SITES_URL = XML_URL_BASE + "siteList.xml";
    private List<Place> places;

    public XMLWrapper() {
        Serializer serializer = new Persister(getDecodeStrategy());
        URL siteURL;
        try {
            siteURL = new URL(SITES_URL);
            sites = serializer.read(SiteList.class, siteURL.openStream());
        } catch (Exception ex) {
            throw new XMLCreationException("Could not read or deserialize site list from: " + SITES_URL, ex);
        }
        makePlaces();
    }

    public List<Place> getPlaces() {
        return places;
    }

    public WeatherData getWeatherData(EnvCanPlace place) {
    	EnvCanWeatherData result = null;
        try {
            SiteData data = getSiteData(place.getSite());
            CurrentConditions current = data.getCurrentConditions();
            double currentTemp = current.getTemperature().getValue();
            ForecastGroup fg = data.getForecastGroup();
            Forecast today = fg.getTodayForecast();
            Forecast tonight = fg.getTonightForecast();
            double high = Double.MIN_VALUE;
            double low = Double.MAX_VALUE;
            String comingConditions = null;
            if (today == null) {
                high = currentTemp;
            } else {
                high = today.getHigh().getValue();
                comingConditions = today.getBasicForecast();
            }

            if (tonight == null) {
                low = currentTemp;
            } else {
                low = tonight.getLow().getValue();
                if (comingConditions == null) {
                    comingConditions = tonight.getBasicForecast();
                }
            }
            result = new EnvCanWeatherData(place, currentTemp,
                    current.getCondition(), high, low, comingConditions, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private void tempMakePlaces() {
        try {
            // TODO this is faked/hardcoded.  Make it real.
            Site tSite  = new Site("s0000458", "Toronto", "Toronto", "ON");
            Place toronto = new EnvCanPlace(tSite, getCoords(tSite));
            places = new ArrayList<Place>();
            places.add(toronto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makePlaces() {
        places = new ArrayList<Place>();
        for (Site site : sites.getSites()) {
            Coordinates coords;
            try {
                coords = getCoords(site);
                places.add(new EnvCanPlace(site, coords));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Coordinates getCoords(Site site) throws Exception {
        SiteData data = getSiteData(site);
        Name name  = data.getLocation().getName();
        return new Coordinates(new EnvCanLatitude(name.getLatitude()),
                new EnvCanLongitude(name.getLongitude()));
    }

    private SiteData getSiteData(Site site) throws Exception {
        URL siteDataURL = getSiteDataURL(site);
        Serializer serializer = new Persister(getDecodeStrategy());
        return serializer.read(SiteData.class, siteDataURL.openStream());
    }

    private URL getSiteDataURL(Site site) throws MalformedURLException {
        String urlString = XML_URL_BASE + site.getProvinceCode() +
                "/" + site.getSiteCode() + "_e" +".xml"; // TODO: re "_e" use e or f based on lang setting.
        return new URL(urlString);
    }

    private Strategy getDecodeStrategy() {
        // The com.vanaltj.canweather.envcan.xml.sitedata.Temperature class has a "class" Attribute
        // This is the workaround, to prevent ClassNotFound errors from "low" and "high"
        // http://stackoverflow.com/questions/8862548/simple-xml-framework-on-android-class-attribute
        Strategy strategy = new TreeStrategy("clazz", "len");
        return strategy;
    }
}
