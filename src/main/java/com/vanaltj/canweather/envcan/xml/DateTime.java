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
