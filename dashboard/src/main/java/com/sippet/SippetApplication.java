package com.sippet;

import com.sippet.configuration.SippetDasbhaordConfiguration;
import com.sippet.configuration.SippetDomainConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({SippetDasbhaordConfiguration.class, SippetDomainConfiguration.class, })
@SpringBootApplication
public class SippetApplication {
    public static void main(String[] args) {
        SpringApplication.run(SippetApplication.class, args);
    }
}
