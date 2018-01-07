package com.sippet.configuration;

import com.sippet.domain.Domains;
import com.sippet.service.Services;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class, Domains.class})
@ComponentScan(basePackageClasses = Services.class)
@Configuration
public class SippetDomainConfiguration {
}
