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
