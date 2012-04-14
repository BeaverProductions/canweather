package com.vanaltj.canweather.envcan.data.location;

import com.vanaltj.canweather.data.location.Longitude;

public class EnvCanLongitude extends Longitude {

    public EnvCanLongitude(String xmlValue) {
        super(parseDegrees(xmlValue), parseDirection(xmlValue));
    }

    private static Direction parseDirection(String xmlValue) {
        // TODO Auto-generated method stub
        return null;
    }

    private static Double parseDegrees(String xmlValue) {
        // TODO Auto-generated method stub
        return null;
    }

}
