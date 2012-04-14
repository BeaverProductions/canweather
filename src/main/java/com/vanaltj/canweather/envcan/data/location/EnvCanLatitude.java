package com.vanaltj.canweather.envcan.data.location;

import com.vanaltj.canweather.data.location.Latitude;

class EnvCanLatitude extends Latitude {

    public EnvCanLatitude(String xmlValue) {
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
