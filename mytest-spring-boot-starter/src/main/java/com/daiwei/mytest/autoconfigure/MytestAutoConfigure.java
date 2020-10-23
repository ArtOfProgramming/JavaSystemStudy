package com.daiwei.mytest.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MytestProperties.class)
@ConditionalOnClass(MytestService.class)
@ConditionalOnProperty(prefix = "mytest", value = "enabled", matchIfMissing = true)
public class MytestAutoConfigure {

    @Autowired
    private MytestProperties mytestProperties;

    @Bean
    @ConditionalOnMissingBean(MytestService.class)
    public MytestService customerService() {
        MytestService customerService = new MytestService();
        customerService.setMytestProperties(mytestProperties);
        return customerService;
    }
}
