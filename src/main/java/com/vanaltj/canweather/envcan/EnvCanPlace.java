package com.vanaltj.canweather.envcan;

import com.vanaltj.canweather.data.Place;
import com.vanaltj.canweather.data.location.Coordinates;
import com.vanaltj.canweather.envcan.xml.sitelist.Site;

public class EnvCanPlace implements Place {

    private final String name;
    private final Site site;

    public EnvCanPlace(Site site) {
        // TODO: Menu option for French.
        name = site.getEnglishName();
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public Site getSite() {
        return site;
    }
}
