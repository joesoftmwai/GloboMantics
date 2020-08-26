package com.joesoft.globomantics.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MessageService {
    @GET("messages")
    Call<String> getMessages();

    // retrieve messages from an alternate URL
//    @GET
//    Call<String> getMessages(@Url String altUrl);
}
