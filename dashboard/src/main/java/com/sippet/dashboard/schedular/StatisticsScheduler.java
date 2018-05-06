package com.sippet.dashboard.schedular;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class StatisticsScheduler {
    @Scheduled(cron = "0 0 16 * * *")
    public void statisticsScheduling() {
        System.out.println("statisticsScheduling method!!!");

        
    }
}
