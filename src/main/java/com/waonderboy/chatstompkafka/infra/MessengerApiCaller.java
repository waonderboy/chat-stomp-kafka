package com.waonderboy.chatstompkafka.infra;

import com.waonderboy.chatstompkafka.infra.command.MessengerApiCommand;
import com.waonderboy.chatstompkafka.infra.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessengerApiCaller {
    private final RetrofitUtils retrofitUtils;
    private final RetrofitMessengerApi messengerApiCaller;

    public MessengerApiResponse.send sendMessage(String token, MessengerApiCommand.send request) {
        var call = messengerApiCaller.sendMessage(request, token);
        return retrofitUtils.responseSync(call)
                .orElseThrow(RuntimeException::new);
    }

}
