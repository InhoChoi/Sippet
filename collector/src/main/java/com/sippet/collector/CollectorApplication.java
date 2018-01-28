package com.sippet.collector;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@SpringBootApplication
@PropertySource("classpath:application-collector.properties")
public class CollectorApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(CollectorApplication.class, args);
    }

    final static String queueName = "testMQ";

    @Bean
    ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost", 5672);
    }

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(queueName);
    }

    @Bean
    AmqpTemplate amqpTemplate() {
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setRoutingKey("testMQ");
        template.setMessageConverter(new SimpleMessageConverter());
        return template;
    }

    @Autowired
    Producer producer;

    @Scheduled(fixedDelay = 500L)
    public void sendMessage() {
        System.out.println("Send Message");
        producer.sendTo(queueName, "Hi. This is multi module rabbitmq test!");
    }
}
