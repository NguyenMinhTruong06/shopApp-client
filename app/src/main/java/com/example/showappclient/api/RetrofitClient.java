package com.example.showappclient.api;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://2736-1-53-89-196.ngrok-free.app/";
    private static String accessToken = "";
    //eyJhbGciOiJIUzI1NiJ9.eyJwaG9uZU51bWJlciI6IjAxMTEyMjIzMzMiLCJzdWIiOiIwMTExMjIyMzMzIiwiZXhwIjoxNzE2OTA3NjE4fQ.1B1fShH10wJlsP0rLZDoy7e9TcA6Vliw1W7LOlD69-E

    public static void updateAccessToken(String token) {
        accessToken = token;
    }

    private static Retrofit retrofit;


    private static OkHttpClient client;

    public static Retrofit getInstance() {
        if (client == null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(chain -> {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("Authorization", "Bearer " + accessToken)
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    })
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES);

            client = httpClient.build();
        }

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }


    public static ApiInterface getApiService() {
        return getInstance().create(ApiInterface.class);
    }
}
