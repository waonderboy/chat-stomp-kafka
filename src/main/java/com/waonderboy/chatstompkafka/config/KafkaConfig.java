package com.waonderboy.chatstompkafka.config;


import org.springframework.context.annotation.Configuration;


/**
 * 여기서 serializer deserializer bootstrap-server 설정시 application.yml 과 충돌 발생
 * local kafka 서버와는 충돌 발생하지 않는데 이유는 찾아봐야 할듯
 */
//@EnableKafka
@Configuration
public class KafkaConfig {}
