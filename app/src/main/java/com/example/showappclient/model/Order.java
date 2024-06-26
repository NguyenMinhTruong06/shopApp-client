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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
