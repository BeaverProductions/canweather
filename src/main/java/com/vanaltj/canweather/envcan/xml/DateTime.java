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

    @Attribute(name="UTCOffset")
    private int utcOffset;

    @Element(name="year")
    private int year;

    @Element(name="month")
    private int month;

    @Element(name="day")
    private int day;

    @Element(name="hour")
    private int hour;

    @Element(name="minute")
    private int minute;

    private Calendar calendar;

    public DateTime(@Attribute(name="name") String name, @Attribute(name="zone") String zone,
            @Attribute(name="UTCOffset") int utcOffset, @Element(name="year") int year,
            @Element(name="month") int month, @Element(name="day") int day,
            @Element(name="hour") int hour, @Element(name="minute") int minute) {
        this.name = name;
        this.zone = zone;
        this.utcOffset = utcOffset;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        calendar = new GregorianCalendar();
        calendar.set(this.year, this.month, this.day, this.hour, this.minute, 0);
        calendar.set(Calendar.ZONE_OFFSET, this.utcOffset);
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
