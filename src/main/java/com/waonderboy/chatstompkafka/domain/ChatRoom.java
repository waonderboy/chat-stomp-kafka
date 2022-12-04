package com.waonderboy.chatstompkafka.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter @Setter
public class ChatRoom {

    @Id
    @GeneratedValue
    private Long id;
    private String roomId;
    private String name;

    protected ChatRoom() {}

    @Builder
    public ChatRoom(String name) {
        this.roomId = UUID.randomUUID().toString();
        this.name = name;
    }
}
