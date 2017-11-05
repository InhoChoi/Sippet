package com.sippet;

import com.sippet.domain.Domains;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class, Domains.class})
@SpringBootApplication
public class SippetApplication {
    public static void main(String[] args) {
        SpringApplication.run(SippetApplication.class, args);
    }
}
