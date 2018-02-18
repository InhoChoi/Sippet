package com.sippet.collector;

import com.sippet.domain.domain.usertrack.UserTrackDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserTrackProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void produce(UserTrackDto userTrackDto) {
        amqpTemplate.convertAndSend(userTrackDto);
    }
}
