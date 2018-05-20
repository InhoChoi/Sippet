package com.sippet.consumer;

import com.sippet.domain.builder.UserTrackBuilder;
import com.sippet.domain.database.retention.RetentionPeriodRepository;
import com.sippet.domain.database.usertrack.UserTrack;
import com.sippet.domain.database.usertrack.UserTrackDto;
import com.sippet.domain.database.usertrack.UserTrackRepository;
import com.sippet.domain.service.PeriodCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class UserTrackConsumer {
    @Autowired
    private UserTrackBuilder userTrackBuilder;

    @Autowired
    private UserTrackRepository userTrackRepository;

    @Autowired
    private PeriodCalculator periodCalculator;

    @Autowired
    private RetentionPeriodRepository retentionPeriodRepository;

    public void handleMessage(UserTrackDto userTrackDto) {
        log.info("Message : {} ", userTrackDto);

        final UserTrack userTrack = UserTrack.builder()
                .host(userTrackDto.getHost())
                .href(userTrackDto.getHref())
                .referrerHost(userTrackDto.getReferrerHost())
                .referrerPath(userTrackDto.getReferrerPath())
                .pathName(userTrackDto.getPathName())
                .newVisitor(userTrackDto.getNewVisitor())
                .trackingId(userTrackDto.getTrackingId())
                .build();

        if(isValid(userTrack)) {
            retentionPeriodRepository.save(periodCalculator.calculate(userTrackRepository, userTrack.getTrackingId()));
        }
        userTrackRepository.save(userTrack);
    }

    private boolean isValid(UserTrack userTrack) {
        return userTrackRepository.countByTrackingId(userTrack.getTrackingId()) > 0
                && retentionPeriodRepository.checkTodayDataExist(userTrack.getTrackingId(), LocalDate.now()) <= 0;
    }
}
