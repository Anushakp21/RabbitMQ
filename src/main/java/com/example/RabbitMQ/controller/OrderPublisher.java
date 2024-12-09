package com.example.RabbitMQ.controller;

import com.example.RabbitMQ.config.MessagingConfig;
import com.example.RabbitMQ.dto.Order;
import com.example.RabbitMQ.dto.OrderStatus;
import com.example.RabbitMQ.emailservice.EmailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    EmailService emailService;

    @PostMapping("/{restaurantName}")
    public  String bookOrder(@RequestBody Order order,@PathVariable String restaurantName)
    {
      order.setOrderId(UUID.randomUUID().toString());
      OrderStatus orderStatus=new OrderStatus(order,"PROCESS","ORDER PLACED SUCCESSFULLY IN RESTAURANT "+restaurantName);
      rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE,MessagingConfig.ROUTING_KEY,orderStatus);

        String toEmail = "mudimidonesh@gmail.com"; // Replace with customer's email
        String subject = "Order Confirmation";
        String body = "Dear Customer,\n\nYour order with ID " + order.getOrderId() +
                " has been successfully placed in " + restaurantName +
                ".\n\nThank you for ordering with us!";
        emailService.setJavaMailSender(toEmail, subject, body);

        return "Order Placed Successfully";
    }

}
