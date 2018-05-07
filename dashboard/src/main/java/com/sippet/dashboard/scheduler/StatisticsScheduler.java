package com.sippet.dashboard.scheduler;

import com.sippet.domain.domain.userstatistics.UserStatistics;
import com.sippet.domain.domain.userstatistics.UserStatisticsRepository;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatisticsScheduler {
    @Autowired
    UserTrackRepository userTrackRepository;

    @Autowired
    UserStatisticsRepository userStatisticsRepository;

    @Scheduled(cron = "*/10 * * * * *")
    public void statisticsScheduling() {
        System.out.println("statisticsScheduling method!!!");

        System.out.println(userTrackRepository.countGroupByHrefAndDate());
    }
}
