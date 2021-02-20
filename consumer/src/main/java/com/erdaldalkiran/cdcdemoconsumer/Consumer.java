package com.erdaldalkiran.cdcdemoconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {
    @KafkaListener(
        topics = "${kafka.topic}",
        groupId = "${kafka.group-id}",
        containerFactory = "kafkaListenerContainerFactory")
    public void listener(@Payload CounterEvent event) {

        log.info("Consumer.listener processing new event {}", event);
    }
}
