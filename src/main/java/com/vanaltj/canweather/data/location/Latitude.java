package com.vanaltj.canweather.data.location;

public class Latitude extends CoordinateComponent {

    public Latitude(double degrees, Direction dir) {
        super(degrees, dir);
    }

    @Override
    public void validateDirection(Direction direction) {
        switch(direction) {
        case NORTH:
            break;
        case SOUTH:
            break;
        case NOWHERE:
            break;
        case EAST:
        case WEST:
        default:
            throw new IllegalArgumentException("Latitude may only be North/South.");
        }
    }

}
