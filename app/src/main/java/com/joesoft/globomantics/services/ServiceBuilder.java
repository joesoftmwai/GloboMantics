package com.joesoft.globomantics.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String URL = "http://192.168.43.128:5000/";
    // create logger
    private static HttpLoggingInterceptor sLogger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    // create OkHttp client
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder().addInterceptor(sLogger);

    private static Retrofit.Builder sBuilder =
            new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    private static Retrofit sRetrofit = sBuilder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return sRetrofit.create(serviceType);
    }
}
