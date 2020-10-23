package com.daiwei;

//import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ServletComponentScan
@MapperScan("com.daiwei.mapper")
public class BootSpringBoot {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BootSpringBoot.class);
    }

}
