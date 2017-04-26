package com.tesla.cloud.example.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * consumer
 */
@EnableBinding(Sink.class)
public class ConsumerService {

    @StreamListener(Sink.INPUT)
    public void playMessage(Object playLoad){
        System.out.println("Received: " + playLoad);
    }

}
