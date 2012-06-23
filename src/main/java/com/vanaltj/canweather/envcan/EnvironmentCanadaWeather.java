package com.vanaltj.canweather.envcan;

import java.util.List;

import com.vanaltj.canweather.WeatherHelper;
import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.data.WeatherData;
import com.vanaltj.canweather.data.location.Coordinates;

public class EnvironmentCanadaWeather implements WeatherHelper {
    private Place toronto;
    private boolean debug;

    private static WeatherHelper INSTANCE;
    private static WeatherHelper DEBUG_INSTANCE;

    private XMLWrapper source;

    private EnvironmentCanadaWeather() {
        this(false);
    }

    private EnvironmentCanadaWeather(boolean debug) {
        this.debug = debug;
        source = new XMLWrapper(debug);
    }

    public static WeatherHelper getInstance(boolean debug) {
        if (debug) {
            if (DEBUG_INSTANCE == null) {
                DEBUG_INSTANCE = new EnvironmentCanadaWeather(true);
            }
            return DEBUG_INSTANCE;
        }
 
        if (INSTANCE == null) {
            INSTANCE = new EnvironmentCanadaWeather();
        }
        return INSTANCE;
    }

    public List<Place> getPlaces() {
        return source.getPlaces();
    }

    public WeatherData getWeather(Place place) {
        EnvCanPlace ePlace = (EnvCanPlace) place;
        return source.getWeatherData(ePlace);
    }

    public WeatherData getClosestWeather(Coordinates point) {
        if (debug) {
            return getWeather(toronto);
        }
        return getWeather(getClosestPlace(point));
    }

    private Place getClosestPlace(Coordinates point) {
        throw new RuntimeException("Not implemented yet.\n" +
                "Please use WeatherHelperFactory.getWeatherHelper(true) to get debug-mode helper.\n" +
                "It will always return Toronto weather.");
    }
}