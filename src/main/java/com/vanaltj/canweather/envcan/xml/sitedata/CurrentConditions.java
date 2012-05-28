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
