package com.sippet.consumer;

import com.sippet.domain.domain.usertrack.UserTrack;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private static Logger log = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private UserTrackRepository userTrackRepository;

    public void handleMessage(String trackObject) {
        log.info("소비자>> " + trackObject);
        log.info("????????????????????");
//        log.info(trackObject.getHost() + ", " + trackObject.getPathName() + ", " + trackObject.getHref());
//        userTrackRepository.save(trackObject);
    }
}
