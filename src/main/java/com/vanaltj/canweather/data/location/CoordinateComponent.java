/*
 * Copyright 2012 Jon VanAlten
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vanaltj.canweather.data.location;

abstract class CoordinateComponent {

    public enum Direction {
        EAST("E"),
        WEST("W"),
        NORTH("N"),
        SOUTH("S"),
        NOWHERE("X");

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

    public CoordinateComponent(double degrees, Direction direction) {
        validateDirection(direction);
        this.degrees = degrees;
        this.direction = direction;
    }

    public abstract void validateDirection(Direction direction);

    public double getDegrees() {
        return degrees;
    }

    public String getDegreesAsString() {
        String asStringFull = Double.toString(degrees);
        int decimalPosition = asStringFull.indexOf('.');
        if ((decimalPosition >= 0) &&
                (decimalPosition < asStringFull.length() - 2)) {
            return asStringFull.substring(0, decimalPosition + 2);
        }
        return asStringFull;
    }

    public Direction getDirection(){
        return direction;
    }
}
