package com.waonderboy.chatstompkafka.messagebroker;

import com.waonderboy.chatstompkafka.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {
    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    @Value("${spring.kafka.topic.notification}")
    private String topic;

    public void send(String roomId, ChatMessage data) {
        log.info("sending data='{}' to topic='{}'", data, topic);
        kafkaTemplate.send(topic, roomId, data);
    }
}
