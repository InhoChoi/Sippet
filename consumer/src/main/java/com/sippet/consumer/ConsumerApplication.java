package com.sippet.consumer;

import com.sippet.domain.domain.Domains;
import com.sippet.domain.domain.usertrack.UserTrackRepository;
import com.sippet.domain.service.Services;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

//@ComponentScan(basePackageClasses = {Domains.class, Consumer.class})
@SpringBootApplication(scanBasePackages = {"com.sippet.domain", "com.sippet.consumer"})
@PropertySource("classpath:application-consumer.properties")
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

//    final static String queueName = "testMQ";
//
//    @Bean
//    ConnectionFactory connectionFactory() {
//        return new CachingConnectionFactory("localhost", 5672);
//    }
//
//    @Bean
//    Queue queue() {
//        return new Queue(queueName, false);
//    }
//
//    @Bean
//    MessageListener messageListener() {
//        return new MessageListenerAdapter(new Consumer(), new SimpleMessageConverter());
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer container() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueNames(queueName);
//        container.setAutoStartup(true);
//        container.setConcurrentConsumers(2);
//        container.setMessageListener(messageListener());
//        return container;
//    }
}
