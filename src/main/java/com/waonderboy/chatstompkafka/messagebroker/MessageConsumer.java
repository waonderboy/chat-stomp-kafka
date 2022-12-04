package com.waonderboy.chatstompkafka.messagebroker;

import com.waonderboy.chatstompkafka.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {

    private static final String TOPIC = "message-01";
    private final SimpMessagingTemplate template;

    @KafkaListener(id = "main-listener", topics = TOPIC)
    public void receive(ChatMessage message) throws Exception {
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
