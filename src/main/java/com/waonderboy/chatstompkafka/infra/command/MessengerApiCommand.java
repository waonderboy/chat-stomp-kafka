package com.waonderboy.chatstompkafka.infra.command;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * {
 *   "recipient": "{id:5696429073727123}",
 *   "messaging_type": "RESPONSE",
 *   "message": "{text:'안녕하신가 정민씨'}"
 * }
 */
public class MessengerApiCommand {

    @Getter
    @Builder
    @ToString
    public static class send {
        private Recipient recipient;
        private String messaging_type;
        private MessageBody message;
    }


    @Getter
    @Builder
    @ToString
    public static class Recipient {
        private final Long id;
    }

    @Getter
    @Builder
    @ToString
    public static class MessageBody {
        private final String text;
    }



}
