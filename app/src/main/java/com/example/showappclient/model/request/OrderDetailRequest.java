package com.example.showappclient.model.request;

import com.google.gson.annotations.SerializedName;

public class OrderDetailRequest {

    @SerializedName("order_id")
    private int orderId;
    @SerializedName("product_id")
    private int productId;
    @SerializedName("number_of_products")
    private int numberOfProducts;
    @SerializedName("total_money")
    private Float totalMoneyProduct;
    @SerializedName("product_option")
    private String option;
    private Float price;

    // Constructor
    public OrderDetailRequest(int orderId, int productId, Float price, int numberOfProducts,Float totalMoneyProduct, String option ) {
        this.orderId = orderId;
        this.productId = productId;
        this.numberOfProducts = numberOfProducts;
        this.totalMoneyProduct = totalMoneyProduct;
        this.option = option;
        this.price = price;
    }

    // Getters and Setters
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
}
