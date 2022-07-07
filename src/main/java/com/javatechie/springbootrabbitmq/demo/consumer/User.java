package com.javatechie.springbootrabbitmq.demo.consumer;

import com.javatechie.springbootrabbitmq.demo.config.MessagingConfig;
import com.javatechie.springbootrabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {
    //@RabbitListener(queues = MessagingConfig.QUEUE)
    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
    public void consumeMessageFromQueue(OrderStatus status){
        System.out.println("Message recieved from queue : " + status);
    }
}
