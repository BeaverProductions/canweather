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

package com.vanaltj.canweather.envcan.xml;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict=false)
public class DateTime {

    @Attribute(name="name")
    private String name;

    @Attribute(name="zone")
    private String zone;

    @Element(name="year", required=false)
    private int year;

    @Element(name="month", required=false)
    private int month;

    @Element(name="day", required=false)
    private int day;

    @Element(name="hour", required=false)
    private int hour;

    @Element(name="minute", required=false)
    private int minute;

    private Calendar calendar;

    public DateTime(@Attribute(name="name") String name, @Attribute(name="zone") String zone,
            @Element(name="year") int year, @Element(name="month") int month,
            @Element(name="day") int day, @Element(name="hour") int hour,
            @Element(name="minute") int minute) {
        this.name = name;
        this.zone = zone;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;

        calendar = new GregorianCalendar();
        calendar.set(this.year, this.month - 1, this.day, this.hour, this.minute, 0);
    }

    public DateTime(@Attribute(name="name") String name, @Attribute(name="zone") String zone) {
        this.name = name;
        this.zone = zone;

        calendar = new GregorianCalendar();
    }

    public String getName() {
        return name;
    }

    public String getZone() {
        return zone;
    }

    public Calendar getDate() {
        return (Calendar) calendar.clone();
    }
}
