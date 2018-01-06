package com.sippet.batch;

import com.google.common.collect.Iterators;
import com.sippet.domain.usertrack.UserTrackRepository;
import com.sippet.service.usertrack.UserTrackFinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
public class RetentionScheduler {
    private static final int READ_SIZE = 100;

    @Autowired
    private UserTrackFinder userTrackFinder;

    //@Scheduled(cron = "0 * * * * *")
    @Scheduled(fixedDelay = 1000L * 60)
    public void calculateRetention() {
        final LocalDateTime todayStart = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        final LocalDateTime beforeMonth = todayStart.minusMonths(1);
        final LocalDateTime todayEnd = todayStart.with(LocalTime.MAX);

        String lastTrackingId = "";
        while (true) {
            List<String> trackingIdList = userTrackFinder.findTrackingId(todayStart, todayEnd, lastTrackingId, READ_SIZE);

            if (trackingIdList.size() == 0) {
                break;
            }

            lastTrackingId = Iterators.getLast(trackingIdList.iterator());

            userTrackFinder.findLastVisitedDay(beforeMonth, todayStart, trackingIdList)
                    .stream()
                    .count();
        }
    }
}
