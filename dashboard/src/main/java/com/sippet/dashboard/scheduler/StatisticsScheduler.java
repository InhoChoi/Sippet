package com.sippet.dashboard.scheduler;

import com.sippet.domain.database.retention.RetentionPeriodRepository;
import com.sippet.domain.database.userstatistics.UserStatistics;
import com.sippet.domain.database.userstatistics.UserStatisticsRepository;
import com.sippet.domain.database.usertrack.UserTrackRepository;
import com.sippet.domain.database.usertrack.UserTrackHrefCount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("StatisticsScheduler statisticsScheduling method!!!");
        final List<UserTrackHrefCount> countList = userTrackRepository.countByHrefOfYesterday();

        if(countList.size() == 0) {
            return ;
        }

//        final List<UserStatistics> statisticsList = new ArrayList<>();
//        for(UserTrackHrefCount count : countList) {
//            statisticsList.add(convert(count));
//        }

        final List<UserStatistics> statisticsList = countList.stream()
                .map(this::convert)
                .collect(Collectors.toList());

        userStatisticsRepository.save(statisticsList);
    }

    private UserStatistics convert(UserTrackHrefCount count) {
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
