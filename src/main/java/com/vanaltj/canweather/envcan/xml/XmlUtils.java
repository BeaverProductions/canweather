package com.vanaltj.canweather.envcan.xml;

import java.util.Calendar;
import java.util.Collection;

public class XmlUtils {

    private XmlUtils() {/*Not to be instantiated*/}

    public static final Calendar selectUtcTime(Collection<DateTime> times) {
        for (DateTime time : times) {
            Calendar cal = time.getDate();
            if (cal.get(Calendar.ZONE_OFFSET) == 0) {
                return cal;
            }
        }
        return null;
    }
}
