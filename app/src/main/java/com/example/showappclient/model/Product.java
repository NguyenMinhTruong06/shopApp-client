package com.example.showappclient.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {
    private String id;
    private String name;
    private double price;
    private long discount;
    private String thumbnail;
    private String description;

    @SerializedName("created_at")
    private String createdAt;
    private byte[] image;





    @SerializedName("updated_at")
    private String updetedAt;

    @SerializedName("category_id")
    private long categoryID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    private int quantity;
    public Product(String id, String name,byte[]image) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.quantity = 1; // Default quantity is 1 when a product is created
    }

    public int getQuantity() {
        return quantity;
    }

    public Product() {
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private List<ProductImage> images;
    private List<ProductOption>options;

    public List<ProductOption> getOptions() {
        return options;
    }

    public void setOptions(List<ProductOption> options) {
        this.options = options;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdetedAt() {
        return updetedAt;
    }

    public void setUpdetedAt(String updetedAt) {
        this.updetedAt = updetedAt;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }
}
