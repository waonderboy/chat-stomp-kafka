package com.waonderboy.chatstompkafka.repository;


import com.waonderboy.chatstompkafka.domain.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
