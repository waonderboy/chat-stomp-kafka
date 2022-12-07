package com.waonderboy.chatstompkafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class FacebookMessengerController {

    @PostMapping("/webhook")
    public ResponseEntity<Object> webhook(@RequestBody String messageBody) {
        log.info("messageBody={}", messageBody);

//        if (resource.getObject().equals("page")) {
//            for(Entry entry : resource.getEntry()) {
//                if(!entry.getMessaging().isEmpty()) {
//                    entry.getMessaging().get(0);
//                }
//            }
//
//            response = new ResponseEntity<>("EVENT_RECEIVED", HttpStatus.OK);
//        }
//        else {
//            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        return new ResponseEntity<>("EVENT_RECEIVED", HttpStatus.OK);
    }

    @GetMapping("/webhook")
    public ResponseEntity<Object> webhook(@RequestParam(name = "hub.verify_token") String token,
                                          @RequestParam(name = "hub.challenge") String challenge,
                                          @RequestParam(name = "hub.mode") String mode) {
        String VERIFY_TOKEN = "new1234!";

        ResponseEntity<Object> response = null;

        if (mode != null && token != null && token != null) {

            // Checks the mode and token sent is correct
            if (mode.equals("subscribe") && token.equals(VERIFY_TOKEN)) {

                response = new ResponseEntity<>(challenge, HttpStatus.OK);
            } else {
                // Responds with '403 Forbidden' if verify tokens do not match
                response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        return response;
    }

}
