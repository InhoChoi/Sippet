package com.sippet.dashboard;

import com.sippet.dashboard.configuration.SippetDasbhaordConfiguration;
import com.sippet.domain.configuration.SippetDomainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({SippetDasbhaordConfiguration.class, SippetDomainConfiguration.class,})
@SpringBootApplication
public class SippetDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(SippetDashboardApplication.class, args);
    }
}
