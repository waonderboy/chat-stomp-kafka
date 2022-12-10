package com.waonderboy.chatstompkafka.messagebroker;

import com.waonderboy.chatstompkafka.domain.ChatMessage;
import com.waonderboy.chatstompkafka.infra.MessengerApiCaller;
import com.waonderboy.chatstompkafka.infra.MessengerApiResponse;
import com.waonderboy.chatstompkafka.infra.command.MessengerApiCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageConsumer {
    @Value("${FACEBOOK_PAGE_ACCESS_TOKEN}")
    private String ACCESS_TOKEN;
    private final SimpMessagingTemplate template;
    private final MessengerApiCaller messengerApiCaller;

    @KafkaListener(id = "main-listener", topics = "${spring.kafka.topic.message}")
    public void receive(ChatMessage message, Acknowledgment acknowledgment) throws Exception {
        log.info("message.getRoomId={}", message.getRoomId());
        log.info("message.getSender={}", message.getSender());
        log.info("message.getBody={}", message.getBody());

        sendToFacebookMessenger(message);

        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//        acknowledgment.acknowledge();
    }

    private void  sendToFacebookMessenger(ChatMessage message) {
        MessengerApiCommand.Recipient recipient = MessengerApiCommand.Recipient.builder().id(5696429073727123L).build();
        MessengerApiCommand.MessageBody messageBody = MessengerApiCommand.MessageBody.builder().text(message.getBody()).build();
        MessengerApiCommand.send request = MessengerApiCommand.send
                .builder()
                .recipient(recipient)
                .messaging_type("RESPONSE")
                .message(messageBody)
                .build();

        messengerApiCaller.sendMessage(ACCESS_TOKEN, request);

    }
}
