package com.example.showappclient.repository.user;

import com.example.showappclient.model.User;
import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.model.response.AuthResponse;

import java.util.Optional;

import retrofit2.Call;
import retrofit2.Response;

public interface UserRepository {

     public Call<AuthResponse> login(UserRequest userRequest);

    public Call<AuthResponse> signup(UserRequest userRequest);

    public Call<AuthResponse> update(UserRequest userRequest);

    public Call<User>getUser();

}
