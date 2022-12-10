package com.waonderboy.chatstompkafka.controller;


import com.waonderboy.chatstompkafka.dto.security.ChatPrincipal;
import com.waonderboy.chatstompkafka.dto.security.CurrentUser;
import com.waonderboy.chatstompkafka.infra.MessengerApiCaller;
import com.waonderboy.chatstompkafka.infra.MessengerApiResponse;
import com.waonderboy.chatstompkafka.infra.command.MessengerApiCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(@CurrentUser ChatPrincipal chatPrincipal) {
        return "index";
    }

    @Value("${FACEBOOK_PAGE_ACCESS_TOKEN}")
    private String ACCESS_TOKEN;
    private final MessengerApiCaller messengerApiCaller;

    @GetMapping("/send")
    public ResponseEntity<MessengerApiResponse.send> index(){

        MessengerApiCommand.Recipient recipient = MessengerApiCommand.Recipient.builder().id(5696429073727123L).build();
        MessengerApiCommand.MessageBody messageBody = MessengerApiCommand.MessageBody.builder().text("So hard!!!").build();
        MessengerApiCommand.send request = MessengerApiCommand.send
                .builder()
                .recipient(recipient)
                .messaging_type("RESPONSE")
                .message(messageBody)
                .build();

        MessengerApiResponse.send response = messengerApiCaller.sendMessage(ACCESS_TOKEN, request);
        log.info("msg={}",request.toString());



        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
