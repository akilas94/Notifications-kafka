package com.singlife.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final Producer producer;

    private final KafkaCountingMessagesComponent kafkaCountingMessagesComponent;

    @Autowired
    public TestController(Producer producer, KafkaCountingMessagesComponent kafkaCountingMessagesComponent) {
        this.producer = producer;
        this.kafkaCountingMessagesComponent = kafkaCountingMessagesComponent;
    }
    @PostMapping("/publish")
    public void messageToTopic(@RequestParam("message") String message){

        this.producer.sendMessage(message);


    }


    @PostMapping("/count")
    public ResponseEntity getCount(@RequestParam("topicName") String topicName){

        return new ResponseEntity(kafkaCountingMessagesComponent.
                getTotalNumberOfMessagesInATopic(topicName), HttpStatus.OK);


    }


}
