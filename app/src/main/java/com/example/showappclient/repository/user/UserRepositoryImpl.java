package com.example.showappclient.repository.user;

import com.example.showappclient.api.RetrofitClient;
import com.example.showappclient.model.User;
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
    @Override
    public Call<AuthResponse> update(UserRequest userRequest) {
        return RetrofitClient.getApiService().update(userRequest);
    }

    @Override
    public Call<User> getUser() {
        return RetrofitClient.getApiService().getUser();
    }

}
