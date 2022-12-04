package com.waonderboy.chatstompkafka.controller;

import com.waonderboy.chatstompkafka.domain.Member;
import com.waonderboy.chatstompkafka.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/")
    public String loginForm(Member member){

        log.info("[GET] Request Index");

        return "index";
    }

    @GetMapping("/join")
    public String joinForm(Member member){
        log.info("[GET] Request Sign Up Form");

        return "join";
    }

    @PostMapping("/join")
    public String join(Member member){
        memberService.register(member);

        log.info("[POST] Request Register Member");
        return "redirect:/";
    }

    @PostMapping("/")
    public String loginPost(Member member) {
        if (memberService.checkMember(member)) {
            return "redirect:/chat";
        }
        return "redirect:/join";
    }

}
