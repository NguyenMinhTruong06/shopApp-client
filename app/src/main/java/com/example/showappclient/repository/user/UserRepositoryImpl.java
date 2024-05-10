package com.example.showappclient.repository.user;

import com.example.showappclient.api.RetrofitClient;
import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.model.response.AuthResponse;

import retrofit2.Call;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public Call<AuthResponse> login(UserRequest userRequest) {
        return RetrofitClient.getApiService().login(userRequest);
    }

    @Override
    public Call<AuthResponse> signup(UserRequest userRequest) {
        return RetrofitClient.getApiService().signup(userRequest);
    }
}
