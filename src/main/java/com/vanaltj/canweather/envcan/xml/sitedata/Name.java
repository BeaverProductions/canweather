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

package com.vanaltj.canweather.envcan.xml.sitedata;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root
public class Name {

    @Attribute(name="code")
    private String code;

    @Attribute(name="lat")
    private String lat;

    @Attribute(name="lon")
    private String lon;

    @Text
    private String name;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getLatitude() {
        return lat;
    }

    public String getLongitude() {
        return lon;
    }
}
