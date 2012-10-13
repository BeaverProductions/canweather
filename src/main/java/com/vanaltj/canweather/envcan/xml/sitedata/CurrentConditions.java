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

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.vanaltj.canweather.envcan.xml.DateTime;

@Root(strict=false)
public class CurrentConditions {

    @ElementList(inline=true)
    private List<DateTime> times;

    @Element(name="condition", required=false)
    private String condition;

    @Element(name="temperature")
    private Temperature temperature;

    private Calendar time;

    public CurrentConditions(@ElementList(inline=true) List<DateTime> times,
            @Element(name="condition", required=false) String condition,
            @Element(name="temperature") Temperature temperature) {
        this.times = times;
        this.condition = condition;
        this.temperature = temperature;

        time = selectUtcTime(this.times);
    }

    public Calendar getReportedTime() {
        return (Calendar) time.clone();
    }

    public String getCondition() {
        return condition;
    }

    public Temperature getTemperature() {
        return temperature;
    }
}
