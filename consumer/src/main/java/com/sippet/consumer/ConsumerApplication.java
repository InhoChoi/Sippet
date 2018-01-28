package com.sippet.consumer;

import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-consumer.properties")
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("127.0.0.1", 5672);
    }

    @Bean
    Queue queue() {
        return new Queue("testMQ", false);
    }

    @Bean
    MessageListener messageListener() {
        return new MessageListenerAdapter(new Consumer(), new SimpleMessageConverter());
    }

    @Bean
    public SimpleMessageListenerContainer container() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueueNames("testMQ");
        container.setAutoStartup(true);
        container.setConcurrentConsumers(2);
        container.setMessageListener(messageListener());
        return container;
    }
}
