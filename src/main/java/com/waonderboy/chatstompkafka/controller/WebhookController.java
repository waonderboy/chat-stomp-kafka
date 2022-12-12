package com.waonderboy.chatstompkafka.controller;

import com.waonderboy.chatstompkafka.domain.ChatMessage;
import com.waonderboy.chatstompkafka.domain.Member;
import com.waonderboy.chatstompkafka.dto.security.request.MessageRequest;
import com.waonderboy.chatstompkafka.messagebroker.MessageProducer;
import com.waonderboy.chatstompkafka.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/webhook")
public class WebhookController {

    private final MessageProducer messageProducer;
    private final MemberService memberService;
    @Value("${FACEBOOK_PAGE_ACCESS_TOKEN}")
    private String ACCESS_TOKEN;
    private List<String> senderList = new ArrayList<>();
    private List<String> messageList = new ArrayList<>();

    /**
     * 하드코딩이므로 리팩토링 필요
     */
    @PostMapping
    public ResponseEntity<String> webhook(@RequestBody MessageRequest messageRequest) {


        log.info("messageBody={}", messageRequest);

        messageRequest.getEntry().forEach(entry -> {
            entry.getMessaging().forEach(message -> {
                String senderId = message.getSender().getId();
                String messageBody = message.getMessage().getText();

                senderList.add(senderId);
                messageList.add(messageBody);
                if (memberService.existMember(senderId)) {
                    log.info("exist");
                } else {
                    memberService.register(Member.builder().username(senderId).password("22").build());
                }

                ChatMessage message1 = ChatMessage.builder()
                        .roomId("1")
                        .sender(senderId)
                        .body(messageBody)
                        .build();

                messageProducer.send(message1.getRoomId(), message1);
            });
        });

        // TODO: 방이 없으면 방을 만들고 "방을 키 값으로" 카프카 브로커에 "메세지 전송" 및 "알림 전송"
        // TODO: 추후에 상담원이 방에 입장하면 밀린 메세지를 읽어올 수 있음
        // TODO: 하나의 채널에 하나의 티켓만



        return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Object> webhook(@RequestParam(name = "hub.verify_token") String token,
                                          @RequestParam(name = "hub.challenge") String challenge,
                                          @RequestParam(name = "hub.mode") String mode) {

        log.info("token={}", ACCESS_TOKEN);
        ResponseEntity<Object> response = null;

        if (mode != null && token != null && token != null) {
            // Checks the mode and token sent is correct
            if (mode.equals("subscribe") && token.equals(ACCESS_TOKEN)) {
                response = new ResponseEntity<>(challenge, HttpStatus.OK);
            } else {
                // Responds with '403 Forbidden' if verify tokens do not match
                response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return response;
    }



}