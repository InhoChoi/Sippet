package com.sippet.collector;

import com.sippet.domain.domain.usertrack.UserTrack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Producer {
    private static Logger log = LoggerFactory.getLogger(Producer.class);

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

//    public Producer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

//    final static String queueName = "testMQ";
//
//    private CachingConnectionFactory cachingConnectionFactory;
//
//    @Bean
//    ConnectionFactory connectionFactory() {
//        //return new CachingConnectionFactory("localhost", 5672);
//        if(NullChecker.check(cachingConnectionFactory)) {
//            cachingConnectionFactory = new CachingConnectionFactory("localhost", 5672);
//        }
//
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    Queue queue() {
//        return new Queue(queueName, false);
//    }
//
//    @Bean
//    TopicExchange exchange() {
//        return new TopicExchange("spring-boot-exchange");
//    }
//
//    @Bean
//    Binding binding(Queue queue, TopicExchange topicExchange) {
//        log.info("check exchange" + topicExchange.getName());
//        return BindingBuilder.bind(queue).to(topicExchange).with(queueName);
//    }
//
//    @Bean
//    AmqpTemplate amqpTemplate() {
//        RabbitTemplate template = new RabbitTemplate();
//        template.setConnectionFactory(connectionFactory());
//        template.setRoutingKey("testMQ");
//        //template.setQueue(queue());
//        template.setMessageConverter(new SimpleMessageConverter());
//        return template;
//    }

    public static void sendTo(String routingKey, String trackObject) {
        log.info("전송>>...");
        MQProducerConfiguration.amqpTemplate().convertAndSend(routingKey, trackObject);
        //this.rabbitTemplate.convertAndSend(routingKey, message);
    }
}
