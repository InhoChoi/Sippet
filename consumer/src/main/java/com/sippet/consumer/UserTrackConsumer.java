package com.sippet.consumer;

import com.sippet.domain.domain.retention.RetentionPeriodRepository;
import com.sippet.domain.domain.usertrack.UserTrack;
import com.sippet.domain.domain.usertrack.UserTrackDto;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import com.sippet.domain.service.RetentionPeriod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserTrackConsumer {
    @Autowired
    private UserTrackRepository userTrackRepository;

    @Autowired
    private RetentionPeriod retentionPeriod;

    @Autowired
    private RetentionPeriodRepository repository;

    public void handleMessage(UserTrackDto userTrackDto) {
        log.info("Message : {} ", userTrackDto);

        final UserTrack userTrack = UserTrack.builder()
                .host(userTrackDto.getHost())
                .href(userTrackDto.getHref())
                .referrer_host(userTrackDto.getReferrer_host())
                .referrer_path(userTrackDto.getReferrer_path())
                .pathName(userTrackDto.getPathName())
                .newVisitor(userTrackDto.getNewVisitor())
                .trackingId(userTrackDto.getTrackingId())
                .build();
//        RetentionPeriod retentionPeriod = new RetentionPeriod();
        retentionPeriod.produce(userTrack.getTrackingId());

        userTrackRepository.save(userTrack);
    }
}
