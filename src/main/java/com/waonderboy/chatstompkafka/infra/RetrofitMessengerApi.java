package com.waonderboy.chatstompkafka.infra;

import com.waonderboy.chatstompkafka.infra.command.MessengerApiCommand;
import retrofit2.Call;
import retrofit2.http.*;

public interface RetrofitMessengerApi {

    @POST("/me/messages")
    Call<MessengerApiResponse.send> sendMessage(@Body MessengerApiCommand.send request, @Query("access_token") String token);

}
