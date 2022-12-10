package com.waonderboy.chatstompkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:/app.properties")
@SpringBootApplication
public class ChatStompKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatStompKafkaApplication.class, args);
    }

}
