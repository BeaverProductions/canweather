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

import java.util.Set;

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

    @SuppressWarnings("unchecked")
    public Set<Place> getPlaces() {
        return (Set<Place>) source.getPlaces();
    }

    public WeatherData getWeather(Place place) {
        EnvCanPlace ePlace = (EnvCanPlace) place;
        return source.getWeatherData(ePlace);
    }

    public WeatherData getClosestWeather(Coordinates point) {
        if (debug) {
            return getWeather(toronto);
        }
        return source.getClosestWeatherData(point);
    }

    public void prepareCoordinates() {
        source.prepareForCoordinateSearch();
    }
}
