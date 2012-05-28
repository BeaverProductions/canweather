package com.vanaltj.canweather.data.location;

public class Longitude extends CoordinateComponent {

    public Longitude(double degrees, Direction dir) {
        super(degrees, dir);
    }

    @Override
    public void validateDirection(Direction direction) {
        switch(direction) {
        case EAST:
            break;
        case WEST:
            break;
        case NOWHERE:
            break;
        case NORTH:
        case SOUTH:
        default:
            throw new IllegalArgumentException("Longitude may only be East/West.");
        }
    }

}
