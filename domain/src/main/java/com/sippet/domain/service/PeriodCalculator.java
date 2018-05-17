package com.sippet.domain.service;

import com.sippet.domain.domain.retention.RetentionPeriod;
import com.sippet.domain.domain.retention.RetentionPeriodRepository;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import com.sippet.domain.util.Dates;
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
     * Produce Retention period object of calculated period
     *
     * @param userTrackRepo
     * @param trackingId
     * @return Class RetentiodPeriod object of calculated period
     */
    public RetentionPeriod calculate(UserTrackRepository userTrackRepo, String trackingId) {
        final Long validPeriod
                = Dates.calculateDays(userTrackRepo.findTopByTrackingIdOrderByIdDesc(trackingId).getCreatedAt(), Dates.getToady());

        return new RetentionPeriod().builder()
                .trackingId(trackingId)
                .retentionPeriod(validPeriod)
                .eventDate(LocalDate.now())
                .build();
    }
}
