package com.waonderboy.chatstompkafka.dto.security.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.List;



@Getter
@Setter
@ToString
public class MessageRequest {

    private String object;
    private List<Entry> entry;


    @Getter
    @Setter
    @ToString
    public static class Entry {
        private String id;
        private long time;
        private List<Messaging> messaging;
    }

    @Getter
    @Setter
    @ToString
    public static class Messaging {
        private Sender sender;
        private Recipient recipient;
        private long timestamp;
        private Message message;
    }

    @Getter
    @Setter
    @ToString
    public static class Sender {
        private String id;
    }

    @Getter
    @Setter
    @ToString
    public static class Recipient {
        private String id;
    }

    @Getter
    @Setter
    @ToString
    public static class Message {
        private String mid;
        private String text;
    }
}
