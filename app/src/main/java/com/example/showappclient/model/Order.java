package com.example.showappclient.model;

import java.time.LocalDate;
import java.util.Date;
import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("id")
    private Long id;


    @SerializedName("user_id")
    private User user;

    @SerializedName( "fullname")
    private String fullName;

    @SerializedName("phone_number")
    private String phoneNumber;

    @SerializedName( "address")
    private String address;

    @SerializedName("email")
    private String email;

    @SerializedName( "note")
    private String note;

    @SerializedName( "order_date")
    private Date orderDate;

    @SerializedName( "status")
    private String status;

    @SerializedName( "total_money")
    private Float totalMoney;

    @SerializedName( "shipping_method")
    private String shippingMethod;

    @SerializedName( "shipping_address")
    private String shippingAddress;

    @SerializedName( "shipping_date")
    private LocalDate shippingDate;

    @SerializedName( "tracking_number")
    private String trackingNumber;

    @SerializedName( "payment_method")
    private String paymentMethod;
    @SerializedName( "active")
    private Boolean active;












}
