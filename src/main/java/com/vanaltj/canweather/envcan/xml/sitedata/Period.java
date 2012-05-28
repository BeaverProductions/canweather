package com.vanaltj.canweather.envcan.xml.sitedata;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Period {

    public enum PeriodType {

        TODAY("Today"),
        TONIGHT("Tonight"),
        MONDAY("Monday"),
        TUESDAY("Tuesday"),
        WEDNESDAY("Wednesday"),
        THURSDAY("Thursday"),
        FRIDAY("Friday"),
        SATURDAY("Saturday"),
        SUNDAY("Sunday");

        private PeriodType(String asString) {
            periodAsString = asString;
        }

        private String periodAsString;

        @Override
        public String toString() {
            return periodAsString;
        }
    }

    @Attribute(name="textForecastName")
    private String textForecastName;

    @Text
    private String periodDescription;

    private PeriodType periodType;

    public Period(@Attribute(name="textForecastName") String textForecastName,
            @Text String periodDescription) {
        this.textForecastName = textForecastName;
        this.periodDescription = periodDescription;
    }

    public String getPeriod() {
        return textForecastName;
    }

    public PeriodType getPeriodType() {
        cachePeriodTypeIfNeeded();
        return periodType;
    }

    private void cachePeriodTypeIfNeeded() {
        if (periodType == null) {
            for (PeriodType type : PeriodType.values()) {
                if (type.toString().equals(textForecastName)) {
                    periodType = type;
                }
            }
        }
    }

    public String getPeriodDescription() {
        return periodDescription;
    }
}
