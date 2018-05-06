package com.sippet.domain.service;

import com.sippet.domain.domain.retention.RetentionPeriod;
import com.sippet.domain.domain.retention.RetentionPeriodRepository;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class PeriodCalculator {

    @Autowired
    RetentionPeriodRepository retentionPeriodRepository;

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
    private Long calculateDays(LocalDateTime latestDate, LocalDateTime todayDate) {
        return DAYS.between(latestDate.toLocalDate(), todayDate.toLocalDate());
    }

    /**
     * Produce Retention period object of calculated period
     *
     * @param userTrackRepo
     * @param trackingId
     * @return Class RetentiodPeriod object of calculated period
     */
    public RetentionPeriod calculate(UserTrackRepository userTrackRepo, String trackingId) {
        final Long validPeriod
                = calculateDays(userTrackRepo.findTopByTrackingIdOrderByIdDesc(trackingId).getCreatedAt(), getToady());

        return new RetentionPeriod().builder()
                .trackingId(trackingId)
                .retentionPeriod(validPeriod)
                .eventDate(LocalDate.now())
                .build();
    }
}
