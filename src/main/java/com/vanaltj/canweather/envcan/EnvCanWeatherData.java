/*
 * Copyright 2012 Jon VanAlten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
