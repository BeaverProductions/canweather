package com.vanaltj.canweather.data.location;

public class Coordinates {
    private final Latitude lat;
    private final Longitude lon;

    public Coordinates(Latitude lat, Longitude lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Latitude getLatitude() {
        return lat;
    }

    public Longitude getLongitude() {
        return lon;
    }

}
