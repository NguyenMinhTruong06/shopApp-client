package com.example.showappclient.model.request;

import com.google.gson.annotations.SerializedName;

public class UserRequest {
    @SerializedName("role_id")
    public int role;
    @SerializedName("phone_number")
    public String phoneNumber;
    @SerializedName("password")
    public String password;




    @SerializedName("fullname")
    public String name;
    @SerializedName("date_of_birth")
    public String dateOfBirth;
    @SerializedName("retype_password")
    public String retypePassword;

    @SerializedName("address")
    public String address;

    public UserRequest(String phoneNumber, String password){
        this.password=password;
        this.phoneNumber=phoneNumber;
    }

    public UserRequest(String phoneNumber, String password, String name, int role, String retypePassword) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.name = name;
        this.role = role;
        this.retypePassword = retypePassword;
    }
    public UserRequest(String name, String dateOfBirth, String address){
        this.name=name;
        this.dateOfBirth=dateOfBirth;
        this.address=address;
    }

    public UserRequest(){

    }


}