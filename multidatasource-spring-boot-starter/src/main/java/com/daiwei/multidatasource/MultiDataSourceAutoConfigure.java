package com.daiwei.multidatasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.util.StringUtils;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableConfigurationProperties(MultiDataSourceProperties.class)
public class MultiDataSourceAutoConfigure {

    @Autowired
    private MultiDataSourceProperties multiDataSourceProperties;

    @Bean(name = "dynamicDataSource")
    @Primary
    public MultiDynamicDataSource dataSource() throws Exception {
        Map<Object, Object> targetDataSources = new LinkedHashMap<>();
        String DefaultDataSourceKey = null;
        if (!multiDataSourceProperties.getDataSourcePropertyMap().isEmpty()) {
            try {
                Set<Entry<String, Properties>> entries = multiDataSourceProperties.getDataSourcePropertyMap()
                    .entrySet();

                for (Entry<String, Properties> entry : entries) {
                    if (DruidDataSource.class.getName().equals(entry.getValue().getProperty("type"))) {
                        DataSource dataSource = DruidDataSourceFactory.createDataSource(entry.getValue());
                        targetDataSources.put(entry.getKey(), dataSource);
                        if (StringUtils.isEmpty(DefaultDataSourceKey)) {
                            DefaultDataSourceKey = entry.getKey();
                        }
                    }
                }
            } catch (Exception e) {
                throw new Exception("创建多数据源失败" + e);
            } finally {
                //TODO: 处理已连接的数据池，关闭并返回
            }

        } else {
            throw new Exception("数据源配置为空，请配置好数据源");
        }
        return new MultiDynamicDataSource((DataSource) targetDataSources.get(DefaultDataSourceKey), targetDataSources);
    }
}
