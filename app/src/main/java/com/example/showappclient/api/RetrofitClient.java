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

    private static final String BASE_URL = "https://5c14-42-115-248-137.ngrok-free.app/";
    private static String accessToken = "";

    public static void updateAccessToken(String token) {
        accessToken = token;
    }

    private static Retrofit retrofit;

    //    public static Retrofit getInstance() {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .connectTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .writeTimeout(30, TimeUnit.SECONDS);
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(builder.build())
//                    .build();
//        }
//
//        return retrofit;
//
//    }
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
