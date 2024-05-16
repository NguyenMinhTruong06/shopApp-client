package com.example.showappclient.model.request;

import com.example.showappclient.api.RetrofitClient;
import com.example.showappclient.model.response.AuthResponse;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;

public class ChangePasswordRequest {

    @SerializedName("new_password")
    public String newPassword;
    @SerializedName("confirm_new_password")
    public String newConfirmPassword;

    @SerializedName("password")
    public String password;
    public ChangePasswordRequest(String password, String newPassword, String newConfirmPassword){
        this.password=password;
        this.newPassword=newPassword;
        this.newConfirmPassword=newConfirmPassword;
    }


}
