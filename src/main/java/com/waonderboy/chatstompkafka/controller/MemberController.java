package com.waonderboy.chatstompkafka.controller;

import com.waonderboy.chatstompkafka.domain.Member;
import com.waonderboy.chatstompkafka.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginForm(){

        log.info("[GET] Request Index");

        return "login";
    }

    @GetMapping("/join")
    public String joinForm(Member member){
        log.info("[GET] Request Sign Up Form");

        return "join";
    }

    @PostMapping("/join")
    public String join(Member member){
        memberService.register(member);

        // userAccountService.loginAfterModifyInfo(signUpForm.getEmail()); 인증서 발급

        log.info("[POST] Request Register Member");
        return "redirect:/";
    }

}
