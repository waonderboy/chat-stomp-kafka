package com.waonderboy.chatstompkafka.controller;

import com.waonderboy.chatstompkafka.domain.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * /app 으로 메세지가 들어오면 해당 컨트롤러를 거치게 됨
 * ChatHandler 역할을 함
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template; // Broker로 메세지를 전달

    //"/pub/chat/enter"
    @MessageMapping("/chat/enter")
    public void enter(ChatMessage message){
        log.info("message={}",message.getSender());
        log.info("message={}",message.getBody());
        log.info("message={}",message.getRoomId());
        message.setBody(message.getSender() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message){
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}