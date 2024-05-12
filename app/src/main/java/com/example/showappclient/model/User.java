package com.example.showappclient.model;

import java.time.LocalDate;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.Query;

public class User {
            private Long id;

    private String fullName;
    private String phoneNumber;

    private String address;

    private String password;
    private Integer isActive;

    private String dateOfBirth;

    private Long fbAccountId;

    private Long ggAccountId;

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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
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
