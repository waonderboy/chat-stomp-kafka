package com.waonderboy.chatstompkafka.service;

import com.waonderboy.chatstompkafka.domain.ChatRoom;
import com.waonderboy.chatstompkafka.domain.Member;
import com.waonderboy.chatstompkafka.repository.ChatRoomRepository;
import com.waonderboy.chatstompkafka.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> searchChatRooms() {
        return chatRoomRepository.findAll();
    }

    @Transactional
    public void makeChatRoom(String name) {
        chatRoomRepository.save(ChatRoom.builder()
                .name(name).build());
    }

    public ChatRoom getChatRooms(Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }
}
