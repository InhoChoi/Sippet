package com.sippet.collector;

import com.sippet.domain.service.TrackingResult;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Getter
@Service
public class UserTrackSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private UserTrackDtoConverter userTrackDtoConverter;

    void send(UserTrackRequest userTrackRequest, TrackingResult trackingResult) {
        amqpTemplate.convertAndSend(userTrackDtoConverter.convert(userTrackRequest, trackingResult));
    }
}
