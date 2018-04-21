package com.sippet.dashboard.configuration;

import com.sippet.dashboard.api.DashboardApis;
import com.sippet.dashboard.web.DashboardWebs;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {DashboardApis.class, DashboardWebs.class})
public class SippetDasbhaordConfiguration {
}
