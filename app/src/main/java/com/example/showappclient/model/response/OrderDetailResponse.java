package com.example.showappclient.model.response;

import com.google.gson.annotations.SerializedName;

public class OrderDetailResponse {
    private int id;
    @SerializedName("orderId")
    private int orderId;
    @SerializedName("productId")
    private int productId;
    private double price;
    @SerializedName("numberOfProducts")
    private int numberOfProducts;
    @SerializedName("totalMoney")
    private double totalMoney;
    @SerializedName("option")
    private String option;

    @SerializedName("productName")
    private String productName;
    // Constructor
    public OrderDetailResponse(int id, int orderId, int productId, double price, int numberOfProducts, double totalMoney, String option) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.numberOfProducts = numberOfProducts;
        this.totalMoney = totalMoney;
        this.option = option;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
