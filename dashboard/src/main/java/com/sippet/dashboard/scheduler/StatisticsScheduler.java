package com.sippet.dashboard.scheduler;

import com.sippet.domain.domain.retention.RetentionPeriodRepository;
import com.sippet.domain.domain.userstatistics.UserStatistics;
import com.sippet.domain.domain.userstatistics.UserStatisticsRepository;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class StatisticsScheduler {
    @Autowired
    UserTrackRepository userTrackRepository;

    @Autowired
    RetentionPeriodRepository retentionPeriodRepository;

    @Autowired
    UserStatisticsRepository userStatisticsRepository;

    //TODO. 이 스케쥴러는 새벽 3시즘 돌릴 예정이기 때문에 date의 오늘은 포함하지 않는다.
    @Scheduled(cron = "*/10 * * * * *")
    public void statisticsScheduling() {
        System.out.println("statisticsScheduling method!!!");

        System.out.println(userTrackRepository.countGroupByHrefAndDate());
        System.out.println(userTrackRepository.countGroupByHrefAndDate().size());
        System.out.println(userTrackRepository.countGroupByHrefAndDate().get(0));
//        System.out.println(userTrackRepository.countGroupByHrefAndDate().get(0).getCount());
//        System.out.println(userTrackRepository.countGroupByHrefAndDate().get(0).getHref());
//        System.out.println(userTrackRepository.countGroupByHrefAndDate().get(0).getDate());

//        userStatisticsRepository.save();

        System.out.println(retentionPeriodRepository.getAveragePeriod(userTrackRepository.countGroupByHrefAndDate().get(userTrackRepository.countGroupByHrefAndDate().size() - 1).getDate()));
//        System.out.println(retentionPeriodRepository.getAveragePeriod(LocalDate.of(2018, 4, 30)).size());
//        System.out.println(retentionPeriodRepository.getAveragePeriod(LocalDate.of(2018, 4, 30)).get(0));
    }
}
