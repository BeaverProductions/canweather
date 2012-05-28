package com.vanaltj.canweather.envcan.data.location;

import com.vanaltj.canweather.data.location.Latitude;

public class EnvCanLatitude extends Latitude {

    public EnvCanLatitude(String xmlValue) {
        super(parseDegrees(xmlValue), parseDirection(xmlValue));
    }

    private static Direction parseDirection(String xmlValue) {
        if (xmlValue.length() < 1) {
            return Direction.NOWHERE;
        }
        String directionPart = xmlValue.substring(xmlValue.length()-1);
        if (directionPart.equals(Direction.NORTH.getValue())) {
            return Direction.NORTH;
        }
        if (directionPart.equals(Direction.SOUTH.getValue())) {
            return Direction.SOUTH;
        }
        throw new IllegalArgumentException("Not a valid latitude direction: " + directionPart);
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
