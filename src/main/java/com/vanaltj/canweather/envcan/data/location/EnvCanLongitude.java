package com.vanaltj.canweather.envcan.data.location;

import com.vanaltj.canweather.data.location.Longitude;

public class EnvCanLongitude extends Longitude {

    public EnvCanLongitude(String xmlValue) {
        super(parseDegrees(xmlValue), parseDirection(xmlValue));
    }

    private static Direction parseDirection(String xmlValue) {
        if (xmlValue.length() < 1) {
            return Direction.NOWHERE;
        }
        String directionPart = xmlValue.substring(xmlValue.length()-1);
        if (directionPart.equals(Direction.EAST.getValue())) {
            return Direction.EAST;
        }
        if (directionPart.equals(Direction.WEST.getValue())) {
            return Direction.WEST;
        }
        throw new IllegalArgumentException("Not a valid longitude direction: " + directionPart);
    }

    private static double parseDegrees(String xmlValue) {
        if (xmlValue.length() < 2) {
            // It's a hack, this place will now never be found by coord search (too far away)
            return Double.MAX_VALUE;
        }
        String degreesPart = xmlValue.substring(0, xmlValue.length()-2);
        return Double.parseDouble(degreesPart);
    }

}
