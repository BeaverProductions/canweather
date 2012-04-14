package com.vanaltj.canweather.envcan.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Site implements Comparable<Site> {

    @Attribute(name="code")
    private String code;

    @Element(name="nameEn")
    private String nameEn;

    @Element(name="nameFr")
    private String nameFr;

    @Element(name="provinceCode")
    private String provinceCode;

    public Site(@Attribute(name="code") String code, @Element(name="nameEn") String nameEn,
            @Element(name="nameFr") String nameFr, @Element(name="provinceCode") String provinceCode) {
        this.code = code;
        this.nameEn = nameEn;
        this.nameFr = nameFr;
        this.provinceCode = provinceCode;
    }

    public String getSiteCode() {
        return code;
    }

    public String getEnglishName() {
        return nameEn;
    }

    public String getFrenchName() {
        return nameFr;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public int compareTo(Site o) {
        return nameEn.compareTo(o.getEnglishName());
    }
}
