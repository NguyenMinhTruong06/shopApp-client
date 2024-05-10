package com.example.showappclient.model;

import java.time.LocalDate;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName( "id")
            private Long id;

    @SerializedName( "fullname")
    private String fullName;

    @SerializedName(  "phone_number")
    private String phoneNumber;

    @SerializedName(  "address")
    private String address;

    @SerializedName(  "password")
    private String password;
    @SerializedName( "is_active")
    private Integer isActive;

    @SerializedName(  "date_of_birth")
    private LocalDate dateOfBirth;

    @SerializedName(  "fb_account_id")
    private Long fbAccountId;

    @SerializedName( "gg_account_id")
    private Long ggAccountId;

    @SerializedName(  "role_id")
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getFbAccountId() {
        return fbAccountId;
    }

    public void setFbAccountId(Long fbAccountId) {
        this.fbAccountId = fbAccountId;
    }

    public Long getGgAccountId() {
        return ggAccountId;
    }

    public void setGgAccountId(Long ggAccountId) {
        this.ggAccountId = ggAccountId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
