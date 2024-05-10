package com.example.showappclient.model;

import com.google.gson.annotations.SerializedName;

public class Role {
    @SerializedName("id")
            private Long id;

    @SerializedName(  "name")
    private String name;

    public static String ADMIN ="admin";
    public static String USER = "user";
}
