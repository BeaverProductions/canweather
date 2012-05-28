package com.vanaltj.canweather.envcan;

import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.data.location.Coordinates;
import com.vanaltj.canweather.envcan.xml.sitelist.Site;

public class EnvCanPlace implements Place {

    private final String name;
    private final Coordinates coords;
    private final Site site;

    public EnvCanPlace(Site site, Coordinates coords) {
        // TODO: Menu option for French.
        name = site.getEnglishName();
        this.site = site;
        this.coords = coords;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public Site getSite() {
        return site;
    }
}
