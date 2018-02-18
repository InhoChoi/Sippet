package com.sippet.collector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Producer {
    private static Logger log = LoggerFactory.getLogger(Producer.class);

    public static void sendTo(String routingKey, String trackObject) {
        log.info("전송>>...");
        MQProducerConfiguration.amqpTemplate().convertAndSend(routingKey, trackObject);
        //this.rabbitTemplate.convertAndSend(routingKey, message);
    }
}
