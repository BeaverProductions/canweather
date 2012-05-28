package com.vanaltj.canweather;

import com.vanaltj.canweather.envcan.EnvironmentCanadaWeather;

public class WeatherHelperFactory {

    private WeatherHelperFactory() {
        // Do not instantiate
    }

    public static WeatherHelper getWeatherHelper() {
        return EnvironmentCanadaWeather.getInstance();
    }
}
