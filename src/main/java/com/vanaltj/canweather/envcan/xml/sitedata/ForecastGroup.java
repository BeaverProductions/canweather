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

package com.vanaltj.canweather.envcan.xml.sitedata;

import static com.vanaltj.canweather.envcan.xml.XmlUtils.selectUtcTime;

import java.util.Calendar;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.vanaltj.canweather.envcan.xml.DateTime;
import com.vanaltj.canweather.envcan.xml.sitedata.Period.PeriodType;

@Root(strict=false)
public class ForecastGroup {

    @ElementList(inline=true)
    private List<DateTime> times;

    @ElementList(inline=true, required=false)
    private List<Forecast> forecasts;

    private Calendar time;
    private Forecast today, tonight;
    // This is a horrible hack.  Fix this, find a way we don't have to check this on EVERY call.
    // The damn thing is assembled purely by reflection, so constructor isn't actually called.
    private boolean initDone = false;

    public ForecastGroup(@ElementList(inline=true) List<DateTime> times,
            @ElementList(inline=true) List<Forecast> forecasts) {
        this.times = times;
        this.forecasts = forecasts;
        doInitFluff();
    }

    public ForecastGroup(@ElementList(inline=true) List<DateTime> times) {
        this.times = times;
        doInitFluff();
    }

    private synchronized void doInitFluff() {
        if (!initDone) {
            time = selectUtcTime(this.times);
            initForecasts();
            initDone = true;
        }
    }

    private void initForecasts() {
        if (forecasts != null) {
            today = getForecastForPeriod(PeriodType.TODAY);
            tonight = getForecastForPeriod(PeriodType.TONIGHT);
        }
    }

    private Forecast getForecastForPeriod(PeriodType type) {
        Forecast toReturn = null;
        for (Forecast f : forecasts) {
            if (f.getPeriod().getPeriodType() == type) {
                toReturn = f;
            }
        }
        if (toReturn != null) {
            forecasts.remove(toReturn);
        }
        return toReturn;
    }

    public Calendar getForecastTime() {
        doInitFluff();
        return (Calendar) time.clone();
    }

    public Forecast getTodayForecast() {
        doInitFluff();
        return today;
    }

    public Forecast getTonightForecast() {
        doInitFluff();
        return tonight;
    }

    public List<Forecast> getNextDaysForecasts() {
        doInitFluff();
        return forecasts;
    }
}
