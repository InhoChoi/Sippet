package com.sippet.collector;

import com.sippet.domain.service.Services;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-collector.properties")
@ComponentScan(basePackageClasses = {Services.class, UserTrackController.class})
public class CollectorApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(CollectorApplication.class, args);
    }
}
