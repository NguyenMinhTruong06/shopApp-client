package com.example.showappclient.model;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
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
}
