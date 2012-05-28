package com.vanaltj.canweather.data;

public interface WeatherData {

    public Place getPlace();

    public double getCurrentTemp();

    public String getCurrentConditions();

    public double getTodayHigh();

    public double getTodayLow();

    public String getUpcomingConditions();

    public PredictedForecast getForecast();
}
