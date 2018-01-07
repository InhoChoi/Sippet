package com.sippet.configuration;

import com.sippet.api.Apis;
import com.sippet.web.Webs;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {Apis.class, Webs.class})
public class SippetDasbhaordConfiguration {
}
