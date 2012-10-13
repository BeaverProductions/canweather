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
