package com.example.showappclient.repository.changepassword;

import com.example.showappclient.model.request.ChangePasswordRequest;

import com.example.showappclient.model.response.AuthResponse;

import retrofit2.Call;

public interface ChangePasswordRepository {
    public Call<AuthResponse> updatePass(ChangePasswordRequest changePasswordRequest);
}
