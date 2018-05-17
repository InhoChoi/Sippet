package com.sippet.dashboard;

import com.sippet.dashboard.configuration.SippetDasbhaordConfiguration;
import com.sippet.domain.configuration.SippetDomainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Import({SippetDasbhaordConfiguration.class, SippetDomainConfiguration.class, })
@SpringBootApplication
@EnableScheduling
public class SippetApplication {
    public static void main(String[] args) {
        SpringApplication.run(SippetApplication.class, args);
    }
}
