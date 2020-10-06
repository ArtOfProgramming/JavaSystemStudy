package com.daiwei.config;

import com.alibaba.druid.pool.DruidDataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class TransactionManagerConfig {

    @Bean
    public DruidDataSource druidDataSource() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(properties.getProperty("jdbc.url"));
        druidDataSource.setDriverClassName(properties.getProperty("jdbc.driverClass"));
        druidDataSource.setUsername(properties.getProperty("jdbc.username"));
        druidDataSource.setPassword(properties.getProperty("jdbc.password"));
        return druidDataSource;
    }

    @Bean(name = "jdbc")
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
