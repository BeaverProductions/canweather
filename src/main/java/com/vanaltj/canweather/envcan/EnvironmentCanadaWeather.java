package com.vanaltj.canweather.envcan;

import java.util.List;

import com.vanaltj.canweather.WeatherDataWarehouse;
import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.data.WeatherData;
import com.vanaltj.canweather.data.location.Coordinates;

class EnvironmentCanadaWeather implements WeatherDataWarehouse {

    public List<Place> getWeatherLocations() {
        return null;
    }

    public WeatherData getWeather(Place location) {
        return null;
    }

    public WeatherData getClosestWeather(Coordinates point) {
        return null;
    }
}
