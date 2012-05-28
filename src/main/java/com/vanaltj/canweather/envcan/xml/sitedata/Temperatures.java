package com.vanaltj.canweather.envcan.xml.sitedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import com.vanaltj.canweather.envcan.xml.sitedata.Temperature.TemperatureClass;

public class Temperatures {

    public class TemperatureNotAvailableException extends RuntimeException {
        private static final long serialVersionUID = 7376011101909528355L;
    }

    @Element(name="textSummary", required=false)
    private String textSummary;

    @ElementList(inline=true)
    private List<Temperature> temperatures;

    private Map<TemperatureClass, Temperature> temperatureValueMap = new HashMap<TemperatureClass, Temperature>();
    private static final Temperature NO_EXIST = new Temperature();

    public Temperature getHigh() throws TemperatureNotAvailableException {
        return getTemperature(TemperatureClass.HIGH);
    }

    public Temperature getLow() throws TemperatureNotAvailableException {
        return getTemperature(TemperatureClass.LOW);
    }

    private Temperature getTemperature(TemperatureClass clazz) throws TemperatureNotAvailableException {
        cacheTemperature(clazz);
        Temperature t = temperatureValueMap.get(clazz);
        if (t == NO_EXIST) {
            throw new TemperatureNotAvailableException();
        }
        return t;
    }

    private void cacheTemperature(TemperatureClass type) {
        if (!temperatureValueMap.containsKey(type)) {
            for (Temperature temperature : temperatures) {
                boolean matched = false;
                if (temperature.getTemperatureClass().equals(type)) {
                    temperatureValueMap.put(type, temperature);
                    matched = true;
                }
                if (!matched) {
                    temperatureValueMap.put(type, NO_EXIST);
                }
            }
        }
    }
}
