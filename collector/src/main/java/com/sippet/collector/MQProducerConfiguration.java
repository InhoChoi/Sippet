package com.sippet.collector;

import com.sippet.domain.util.NullChecker;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQProducerConfiguration {
    final static String queueName = "trackMQ";

    private static CachingConnectionFactory producerCachingConnection;

    //@Bean
    private static ConnectionFactory connectionFactory() {
        //return new CachingConnectionFactory("localhost", 5672);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!");
        if(NullChecker.check(producerCachingConnection)) {
            System.out.println("Consumer connection factory.");
            producerCachingConnection = new CachingConnectionFactory("localhost", 5672);
        }

        return producerCachingConnection;
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
        //log.info("check exchange" + topicExchange.getName());
        return BindingBuilder.bind(queue).to(topicExchange).with(queueName);
    }

//    @Bean
    public static AmqpTemplate amqpTemplate() {
        //TODO. Q: 이 template를 클래스 멤버변수로 빼고 private 로 선언해서 재사용하는게 나을까??
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setRoutingKey("trackMQ");
        //template.setQueue(queue());
        template.setMessageConverter(new SimpleMessageConverter());
        return template;
    }
}
