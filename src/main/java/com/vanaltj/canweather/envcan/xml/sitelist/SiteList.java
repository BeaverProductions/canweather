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
