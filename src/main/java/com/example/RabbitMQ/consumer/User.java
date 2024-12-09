//package com.example.RabbitMQ.consumer;
//
//import com.example.RabbitMQ.config.MessagingConfig;
//import com.example.RabbitMQ.dto.OrderStatus;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class User {
//    @RabbitListener(queues = MessagingConfig.QUEUE)
//    public void consumeMessage(OrderStatus orderStatus)
//    {
//        System.out.println("Message recieved from queue : " + orderStatus);
//    }
//
//}
