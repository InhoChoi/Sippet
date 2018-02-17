package com.sippet.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//@ComponentScan(basePackageClasses = {Domains.class, Consumer.class})
@SpringBootApplication(scanBasePackages = {"com.sippet.domain", "com.sippet.consumer"})
@PropertySource("classpath:application-consumer.properties")
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
