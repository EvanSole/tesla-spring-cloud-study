package com.tesla.cloud.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/***
 * producer
 */
@EnableBinding(Source.class)
public class ProducerService {

    @Autowired
    private Source source;

    public void sendMessage(String msg){
       try {
           source.output().send(MessageBuilder.withPayload(msg).build());
       }catch (Exception e){
           e.printStackTrace();
       }
    }

}
