package com.sippet.consumer;

import com.sippet.domain.util.NullChecker;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConsumerConfiguration {
    final static String queueName = "testMQ";
    private static CachingConnectionFactory consumerCachingConnection;

    //@Bean
    ConnectionFactory connectionFactory() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if(NullChecker.check(consumerCachingConnection)) {
            System.out.println("Consumer connection factory.");
            consumerCachingConnection = new CachingConnectionFactory("localhost", 5672);
        }
        return consumerCachingConnection;
        //return new CachingConnectionFactory("localhost", 5672);
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    MessageListener messageListener() {
        return new MessageListenerAdapter(new Consumer(), new SimpleMessageConverter());
    }

    @Bean
    public SimpleMessageListenerContainer container() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames(queueName);
        container.setAutoStartup(true);
        container.setConcurrentConsumers(2);
        container.setMessageListener(messageListener());
        return container;
    }
}
