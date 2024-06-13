package com.example.showappclient.model;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("id")
    private Long orderId;
    @SerializedName("productId")
    private int productId;
    @SerializedName("numberOfProducts")
    private int numberOfProducts;
    @SerializedName("totalMoneyProduct")
    private Float totalMoneyProduct;
    @SerializedName("option")
    private String option;
    private Float price;
    @SerializedName("productName")
    private String productName;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public Float getTotalMoneyProduct() {
        return totalMoneyProduct;
    }

    public void setTotalMoneyProduct(Float totalMoneyProduct) {
        this.totalMoneyProduct = totalMoneyProduct;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
