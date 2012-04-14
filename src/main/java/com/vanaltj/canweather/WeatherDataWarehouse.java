package com.vanaltj.canweather;

import java.util.List;

import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.data.WeatherData;
import com.vanaltj.canweather.data.location.Coordinates;

public interface WeatherDataWarehouse {

    public List<Place> getWeatherLocations();

    public WeatherData getWeather(Place location);

    public WeatherData getClosestWeather(Coordinates point);

}
