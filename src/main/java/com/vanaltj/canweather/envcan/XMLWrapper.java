package com.vanaltj.canweather.envcan;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;

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

    private boolean debug;
    private boolean coordinateSearchReady = false;
    private SiteList sites;

    private static final String XML_URL_BASE = "http://dd.weatheroffice.ec.gc.ca/citypage_weather/xml/";
    private static final String SITES_URL = XML_URL_BASE + "siteList.xml";
    private Map<EnvCanPlace, Coordinates> places;

    public XMLWrapper(boolean debug) {
        this.debug = debug;
        Serializer serializer = new Persister(getDecodeStrategy());
        URL siteURL;
        try {
            siteURL = new URL(SITES_URL);
            sites = serializer.read(SiteList.class, siteURL.openStream());
        } catch (Exception ex) {
            throw new XMLCreationException("Could not read or deserialize site list from: " + SITES_URL, ex);
        }
        if (this.debug) {
            tempMakePlaces();
            return;
        }
        makePlaces();
    }

    public Set<?> getPlaces() {
        return places.keySet();
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

    public void prepareForCoordinateSearch() {
        Iterator<EnvCanPlace> placeIterator = ((Set<EnvCanPlace>)places.keySet()).iterator();
        while (placeIterator.hasNext()) {
            EnvCanPlace place = placeIterator.next();
            places.put(place, getCoordinates(place.getSite()));
        }
        coordinateSearchReady = true;
    }

    public WeatherData getClosestWeatherData(Coordinates point) {
        if (!coordinateSearchReady) {
            throw new IllegalStateException("Must prepareForCoordinateSearch() first!");
        }
        return getWeatherData(getClosestPlace(point));
    }

    private EnvCanPlace getClosestPlace(Coordinates point) {
        throw new RuntimeException("Not implemented yet.\n" +
                "Please use WeatherHelperFactory.getWeatherHelper(true) to get debug-mode helper.\n" +
                "It will always return Toronto weather.");
    }

    // Used only for debug mode.
    private void tempMakePlaces() {
        try {
            Site tSite  = new Site("s0000458", "Toronto", "Toronto", "ON");
            EnvCanPlace toronto = new EnvCanPlace(tSite);
            places = new HashMap<EnvCanPlace, Coordinates>();
            places.put(toronto, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makePlaces() {
        places = new HashMap<EnvCanPlace, Coordinates>();
        for (Site site : sites.getSites()) {
            try {
                places.put(new EnvCanPlace(site), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Coordinates getCoordinates(Site site) {
        SiteData data = null;
        try {
            data = getSiteData(site);
        } catch (Exception e) {
            return null;
        }
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
