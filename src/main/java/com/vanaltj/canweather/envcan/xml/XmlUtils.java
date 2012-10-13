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

package com.vanaltj.canweather.envcan.xml;

import java.util.Calendar;
import java.util.Collection;

public class XmlUtils {

    private XmlUtils() {/*Not to be instantiated*/}

    public static final Calendar selectUtcTime(Collection<DateTime> times) {
        for (DateTime time : times) {
            Calendar cal = time.getDate();
            if (cal.get(Calendar.ZONE_OFFSET) == 0) {
                return cal;
            }
        }
        return null;
    }
}
