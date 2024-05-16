package com.example.showappclient.repository.changepassword;

import com.example.showappclient.api.RetrofitClient;
import com.example.showappclient.model.request.ChangePasswordRequest;
import com.example.showappclient.model.response.AuthResponse;

import retrofit2.Call;

public class ChangePasswordRepositoryImpl implements ChangePasswordRepository{
    @Override
    public Call<AuthResponse> updatePass(ChangePasswordRequest changePasswordRequest) {
        return RetrofitClient.getApiService().updatePass(changePasswordRequest);
    }
}
