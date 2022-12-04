package com.waonderboy.chatstompkafka.controller;


import com.waonderboy.chatstompkafka.dto.security.ChatPrincipal;
import com.waonderboy.chatstompkafka.dto.security.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@CurrentUser ChatPrincipal chatPrincipal) {
        return "index";
    }
}
