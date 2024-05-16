package com.example.showappclient.localdb.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

import javax.annotation.processing.Generated;

@Entity(tableName = "cart")
public class Cart implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "product_name")
    private String productName;
    private int quantity;
    private double price;
    @ColumnInfo(name = "image_path")
    private String imagePath;
    private String description;
    @ColumnInfo(name = "created_at")
    private String createdAt;

    public Cart() {

    }

    public Cart(int id, String productName, int quantity, double price, String imagePath, String description, String createdAt) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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



    public void setSelected(boolean b) {

    }

}
