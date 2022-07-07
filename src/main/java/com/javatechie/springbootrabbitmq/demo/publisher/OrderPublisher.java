package com.javatechie.springbootrabbitmq.demo.publisher;

import com.javatechie.springbootrabbitmq.demo.config.MessagingConfig;
import com.javatechie.springbootrabbitmq.demo.dto.Order;
import com.javatechie.springbootrabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Autowired
    private RabbitTemplate template;

    @Value("${spring.rabbitmq.template.exchange}")
     private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routing;


    @PostMapping("/{restourantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restourantName) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully in " + restourantName);
        template.convertAndSend(exchange, routing, orderStatus);
        return "Success!!";
    }

}
