package com.vanaltj.canweather.envcan;

import java.util.List;

import com.vanaltj.canweather.WeatherHelper;
import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.data.WeatherData;
import com.vanaltj.canweather.data.location.Coordinates;

public class EnvironmentCanadaWeather implements WeatherHelper {
    private Place toronto;

    private static WeatherHelper INSTANCE;

    private XMLWrapper source;

    private EnvironmentCanadaWeather() {
        source = new XMLWrapper();
    }

    public static WeatherHelper getInstance() {
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
        return getWeather(toronto);
    }
}