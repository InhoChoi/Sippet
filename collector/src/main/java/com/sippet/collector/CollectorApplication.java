package com.sippet.collector;

import com.sippet.domain.configuration.SippetDomainConfiguration;
import com.sippet.domain.service.Services;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @Import(value = {SippetDomainConfiguration.class}) 를 써서 configuration에서 해당 모듈을 모두 참조하고 있고 그 configuration 을 참조한다.
 * @ComponentScan(basePackageClasses = {Services.class, UserTrackController.class})
 */
@SpringBootApplication
@Import(value = {SippetDomainConfiguration.class})
@PropertySource("classpath:application-collector.properties")
public class CollectorApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(CollectorApplication.class, args);
    }
}
