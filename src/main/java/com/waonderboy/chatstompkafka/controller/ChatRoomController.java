package com.waonderboy.chatstompkafka.controller;

import com.waonderboy.chatstompkafka.domain.ChatRoom;
import com.waonderboy.chatstompkafka.domain.Member;
import com.waonderboy.chatstompkafka.repository.MemberRepository;
import com.waonderboy.chatstompkafka.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;
    private final MemberRepository memberRepository;

    @GetMapping
    public String getRooms(Model model){

        List<ChatRoom> rooms = chatService.searchChatRooms();
        model.addAttribute("rooms", rooms);

        return "chat/rooms";
    }

    @PostMapping("/room")
    public String createRoom(@RequestParam String name, RedirectAttributes redirectAttributes){

        chatService.makeChatRoom(name);
        redirectAttributes.addAttribute("roomName", name);

        return "redirect:/chat";
    }

    @GetMapping("/room/{roomId}")
    public String joinRoom(@PathVariable Long roomId, Model model){
        Member member = memberRepository.findByUsername("guest").get();
        ChatRoom findChatRoom = chatService.getChatRooms(roomId);
        model.addAttribute("room", findChatRoom);
        model.addAttribute("member", member);

        return "chat/room";
    }


}