package com.daiwei.multidatasource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "multi-datasource")
public class MultiDataSourceProperties  {

    private Map<String, Properties> dataSourcePropertyMap = new LinkedHashMap<>();

    public Map<String, Properties> getDataSourcePropertyMap() {
        return dataSourcePropertyMap;
    }

    public void setDataSourcePropertyMap(Map<String, Properties> dataSourcePropertyMap) {
        this.dataSourcePropertyMap = dataSourcePropertyMap;
    }

}