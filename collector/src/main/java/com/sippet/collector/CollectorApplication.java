package com.sippet.collector;

import com.sippet.domain.service.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@PropertySource("classpath:application-collector.properties")
@ComponentScan(basePackageClasses = {Services.class, UserTrackController.class})
public class CollectorApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(CollectorApplication.class, args);
    }

//    private Logger log = LoggerFactory.getLogger(CollectorApplication.class);
//    final static String queueName = "testMQ";

//    @Bean
//    ConnectionFactory connectionFactory() {
//        log.info("connection check");
//        return new CachingConnectionFactory("localhost", 5672);
//    }
//
//    @Bean
//    Queue queue() {
//        log.info("queue check");
//        return new Queue(queueName, false);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        log.info("exchange check");
//        return new TopicExchange("spring-boot-exchange");
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange topicExchange) {
//        log.info("binding check");
//        return BindingBuilder.bind(queue).to(topicExchange).with(queueName);
//    }
//
//    @Bean
//    AmqpTemplate amqpTemplate() {
//        log.info("amqpTemplate check");
//        RabbitTemplate template = new RabbitTemplate();
//        template.setConnectionFactory(connectionFactory());
//        template.setRoutingKey("testMQ");
//        template.setMessageConverter(new SimpleMessageConverter());
//        return template;
//    }

//    @Autowired
//    Producer producer;
//
//    @Scheduled(fixedDelay = 500L)
//    public void sendMessage() {
//        System.out.println("Send Message");
//        producer.sendTo(queueName, "Hi. This is multi module rabbitmq test!");
//    }
}
