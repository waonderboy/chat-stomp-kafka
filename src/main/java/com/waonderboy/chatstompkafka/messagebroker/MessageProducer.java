package com.waonderboy.chatstompkafka.messagebroker;

import com.waonderboy.chatstompkafka.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {
    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;
    private static final String TOPIC = "message-01";

    public void send(String roomId, ChatMessage data) {
        log.info("sending data='{}' to topic='{}'", data, TOPIC);
        kafkaTemplate.send(TOPIC, roomId, data);
    }
}
