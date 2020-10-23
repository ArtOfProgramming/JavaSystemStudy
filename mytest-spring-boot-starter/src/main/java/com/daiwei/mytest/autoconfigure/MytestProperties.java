package com.daiwei.mytest.autoconfigure;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author daiwei
 * @since 2020.10.22
 * @description 属性
 */

@ConfigurationProperties(prefix = "mytest")
public class MytestProperties {

    private boolean enabled;
    private Map<String, PersonProperties> personPropertiesMap = new LinkedHashMap<>();

    public MytestProperties() {
    }

    public Map<String, PersonProperties> getPersonPropertiesMap() {
        return personPropertiesMap;
    }

    public void setPersonPropertiesMap(
        Map<String, PersonProperties> personPropertiesMap) {
        this.personPropertiesMap = personPropertiesMap;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
