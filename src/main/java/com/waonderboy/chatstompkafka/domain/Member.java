package com.waonderboy.chatstompkafka.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    

    protected Member() {
    }

    @Builder
    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
