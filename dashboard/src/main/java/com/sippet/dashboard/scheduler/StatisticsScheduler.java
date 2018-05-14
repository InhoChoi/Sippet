package com.sippet.dashboard.scheduler;

import com.sippet.domain.domain.retention.RetentionPeriodRepository;
import com.sippet.domain.domain.userstatistics.UserStatistics;
import com.sippet.domain.domain.userstatistics.UserStatisticsRepository;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import com.sippet.domain.domain.usertrack.projection.UserTrackHrefCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    public void statisticsScheduling() {
        System.out.println("statisticsScheduling method!!!");

        //TODO. 이 userTrackRepository.countByHrefOfYesterday() 에서 select와 group by에 따라 count 갯수 기준이 달라진다.
        System.out.println(userTrackRepository.countByHrefOfYesterday());
        System.out.println(userTrackRepository.countByHrefOfYesterday().size());
        System.out.println(userTrackRepository.countByHrefOfYesterday().get(0));

        final List<UserTrackHrefCount> countList = userTrackRepository.countByHrefOfYesterday();

        for(UserTrackHrefCount count : countList) {
            userStatisticsRepository.save(build(count));
        }
    }

    private UserStatistics build(UserTrackHrefCount count) {
        return UserStatistics.builder()
                .host(count.getHost())
                .href(count.getHref())
                .pathName(count.getPathName())
                .referrerHost(count.getReferrerHost())
                .referrerPath(count.getReferrerPath())
                .visitCount(count.getVisitCount())
                .date(count.getDate())
                .build();
    }
}
