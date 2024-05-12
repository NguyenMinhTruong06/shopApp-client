package com.example.showappclient.api;

import com.example.showappclient.model.User;
import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.model.response.AuthResponse;
import com.example.showappclient.model.response.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("/api/v1/users/login")
    Call<AuthResponse> login(@Body UserRequest loginRequest);
    @POST("/api/v1/users/register")
    Call<AuthResponse> signup(@Body UserRequest signupRequest);

    @PUT("/api/v1/users/update")
    Call<AuthResponse> update(@Body UserRequest updateRequest);

    @GET("/api/v1/products/all")
    Call<ProductResponse> getAllProduct(@Query("limit") int limit, @Query("page") int page);
    @GET("/api/v1/users/getuser")
    Call<User> getUser();
}
