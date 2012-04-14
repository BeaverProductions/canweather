package com.vanaltj.canweather.data.location;

abstract class CoordinateComponent {

    public enum Direction {
        EAST("E"),
        WEST("W"),
        NORTH("N"),
        SOUTH("S");

        private final String abbrev;
        private Direction(String abbrev) {
            this.abbrev = abbrev;
        }

        public String getValue() {
            return abbrev;
        }
    };

    private double degrees;
    private Direction direction;

    public CoordinateComponent(Double degrees, Direction direction) {
        validateDirection(direction);
        this.degrees = degrees;
        this.direction = direction;
    }

    public abstract void validateDirection(Direction direction);

    public double getDegrees() {
        return degrees;
    }

    public Direction getDirection(){
        return direction;
    }
}
