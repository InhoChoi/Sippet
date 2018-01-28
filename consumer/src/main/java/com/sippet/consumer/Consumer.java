package com.sippet.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private static Logger log = LoggerFactory.getLogger(Consumer.class);

    public void handleMessage(String message) {
        log.info("소비자>> " + message);
    }
}
