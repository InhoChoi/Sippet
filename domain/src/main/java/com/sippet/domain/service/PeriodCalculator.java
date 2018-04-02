package com.sippet.domain.service;

import com.sippet.domain.domain.retention.RetentionPeriod;
import com.sippet.domain.domain.retention.RetentionPeriodRepository;
import com.sippet.domain.domain.usertrack.UserTrack;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class PeriodCalculator {
    /**
     * Get today date
     *
     * @return today date
     */
    private LocalDateTime getToady() {
        return LocalDateTime.now();
    }

    /**
     * Calculate to get between period of two dates
     *
     * @param latestDate
     * @param todayDate
     * @return between period of two date
     */
    private Long calculate(LocalDateTime latestDate, LocalDateTime todayDate) {
        return DAYS.between(todayDate.toLocalDate(), latestDate.toLocalDate());
    }

    /**
     * Check difference of two dates were over month or not
     *
     * @param latestDate
     * @param todayDate
     * @return {@code true}, if two dates difference was less than month
     */
    private boolean checkValid(LocalDateTime latestDate, LocalDateTime todayDate) {
        return DAYS.between(todayDate.toLocalDate(), latestDate.toLocalDate()) <= 30;
    }

    /**
     * @return
     */
    public RetentionPeriod produce(UserTrackRepository repository, String trackingId) {

        UserTrack userTrack = repository.findTopByTrackingIdOrderByIdDesc(trackingId);

        log.info("User Track Is : {}", userTrack);
        if(checkValid(repository.findTopByTrackingIdOrderByIdDesc(trackingId).getCreatedAt(), getToady())){
            final Long validPeriod =
                    calculate(repository.findTopByTrackingIdOrderByIdDesc(trackingId).getCreatedAt(), getToady());
            log.info("This tracking id's retention period is valid.");

            return new RetentionPeriod().builder()
                    .trackingId(trackingId)
                    .retentionPeriod(validPeriod)
                    .build();
        }
        return null;
    }
}
