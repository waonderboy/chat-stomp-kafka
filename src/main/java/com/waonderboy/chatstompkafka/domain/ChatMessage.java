package com.waonderboy.chatstompkafka.domain;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


//@Entity
@Getter @Setter
public class ChatMessage implements Serializable {

//    @Id
//    @GeneratedValue
//    private Long id;
    private String roomId;
    private String sender;
    private String body;

    protected ChatMessage() {}

    @Builder
    public ChatMessage(String roomId, String sender, String body) {
        this.roomId = roomId;
        this.sender = sender;
        this.body = body;
    }
}
