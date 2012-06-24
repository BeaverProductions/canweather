package com.vanaltj.canweather;

import java.util.Set;

import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.data.WeatherData;
import com.vanaltj.canweather.data.location.Coordinates;

public interface WeatherHelper {

    public Set<Place> getPlaces();

    public WeatherData getWeather(Place place);

    public WeatherData getClosestWeather(Coordinates point);

    public void prepareCoordinates();

}
