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

package com.vanaltj.canweather.envcan.xml.sitelist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root
public class SiteList {

    @ElementList(name="siteList",inline=true)
    private List<Site> sites;

    private Map<String, List<Site>> sitesByProvince = new TreeMap<String, List<Site>>();

    public SiteList(@ElementList(name="siteList",inline=true) List<Site> sites) {
        Collections.sort(sites);
        this.sites = new CopyOnWriteArrayList<Site>(sites);
        populateSitesByProvince();
    }

    private void populateSitesByProvince() {
        for (Site site : sites) {
            String pCode = site.getProvinceCode();
            List<Site> subList = sitesByProvince.get(pCode);
            if (subList == null) {
                subList = new ArrayList<Site>();
                sitesByProvince.put(pCode, subList);
            }
            subList.add(site);
        }
        for (Map.Entry<String, List<Site>> siteEntry : sitesByProvince.entrySet()) {
            siteEntry.setValue(new CopyOnWriteArrayList<Site>(siteEntry.getValue()));
        }
    }

    public List<Site> getSites() {
        return sites;
    }

    public Set<String> getProvinceCodes() {
        return sitesByProvince.keySet();
    }

    public List<Site> getSitesByProvince(String provinceCode) {
        return sitesByProvince.get(provinceCode);
    }
}
