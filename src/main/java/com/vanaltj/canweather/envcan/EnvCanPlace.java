package com.vanaltj.canweather.envcan;

import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.data.location.Coordinates;
import com.vanaltj.canweather.envcan.xml.Site;

public class EnvCanPlace implements Place {

    private String name;
    private Coordinates coords;

    public EnvCanPlace(Site site) {
        // TODO
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoords() {
        return coords;
    }

}
