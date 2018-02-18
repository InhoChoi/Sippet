package com.sippet.domain.configuration;

import com.sippet.domain.domain.Domains;
import com.sippet.domain.service.Services;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = {Domains.class})
@EntityScan(
        basePackageClasses = {Jsr310JpaConverters.class, Domains.class})
@ComponentScan(basePackageClasses = {Services.class, Domains.class})
@Configuration
public class SippetDomainConfiguration {
}
