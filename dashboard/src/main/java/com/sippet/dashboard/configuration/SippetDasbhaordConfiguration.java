package com.sippet.dashboard.configuration;

import com.sippet.dashboard.api.Apis;
import com.sippet.dashboard.scheduler.Schedulers;
import com.sippet.dashboard.web.Webs;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {Apis.class, Webs.class, Schedulers.class})
public class SippetDasbhaordConfiguration {
}
