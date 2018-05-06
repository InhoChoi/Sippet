package com.sippet.dashboard.scheduler;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatisticsScheduler {
    @Scheduled(cron = "*/10 * * * * *")
    public void statisticsScheduling() {
        System.out.println("statisticsScheduling method!!!");
    }
}
