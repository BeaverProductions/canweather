package com.vanaltj.canweather.envcan;

import com.vanaltj.canweather.data.PredictedForecast;
import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.data.WeatherData;

public class EnvCanWeatherData implements WeatherData {

    private Place place;
    private double currentTemp, highTemp, lowTemp;
    private String currentConditions, upcomingConditions;
    private PredictedForecast forecast;

    public EnvCanWeatherData(Place place, double currentTemp, String currentConditions,
            double highTemp, double lowTemp, String upcomingConditions, PredictedForecast forecast) {
        this.place = place;
        this.currentTemp = currentTemp;
        this.currentConditions = currentConditions;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.upcomingConditions = upcomingConditions;
        this.forecast = forecast;
    }

	public Place getPlace() {
	    return place;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public String getCurrentConditions() {
        return currentConditions;
    }

    public double getTodayHigh() {
        return highTemp;
    }

    public double getTodayLow() {
        return lowTemp;
    }

    public String getUpcomingConditions() {
        return upcomingConditions;
    }

    public PredictedForecast getForecast() {
        throw new RuntimeException("getForecast is not yet implemented.");
        //return forecast;
    }

}
