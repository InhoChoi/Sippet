package com.sippet.domain.service;

import com.sippet.domain.database.retention.RetentionPeriod;
import com.sippet.domain.database.retention.RetentionPeriodRepository;
import com.sippet.domain.database.usertrack.UserTrackRepository;
import com.sippet.domain.util.LocalDateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
                = LocalDateTimeUtils.calculateDays(userTrackRepo.findTopByTrackingIdOrderByIdDesc(trackingId).getCreatedAt(), LocalDateTimeUtils.getToady());

        return new RetentionPeriod().builder()
                .trackingId(trackingId)
                .retentionPeriod(validPeriod)
                .eventDate(LocalDate.now())
                .build();
    }
}
