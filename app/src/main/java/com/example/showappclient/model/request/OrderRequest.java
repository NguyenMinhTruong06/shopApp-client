package com.example.showappclient.model.request;

import com.google.gson.annotations.SerializedName;

public class OrderRequest {
    @SerializedName("id")
    private int id;
    @SerializedName("fullname")
    private String name;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("address")
    private String address;
    @SerializedName("shipping_method")
    private String shipMethod;
    @SerializedName("total_money")
    private Float totalMoney;
    @SerializedName("payment_method")
    private String paymentMethod;

    public  OrderRequest(int userId){
        this.userId = userId;
    }

    public OrderRequest(String name, int userId, String phoneNumber, String address, String shipMethod, Float totalMoney, String paymentMethod) {
        this.name = name;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.shipMethod = shipMethod;
        this.totalMoney = totalMoney;
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(String shipMethod) {
        this.shipMethod = shipMethod;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
