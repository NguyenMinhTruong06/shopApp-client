package com.example.showappclient;

public class checkPhonenumber {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }

        // Loại bỏ khoảng trắng và ký tự đặc biệt
        phoneNumber = phoneNumber.trim().replaceAll("[^0-9]", "");

        // Kiểm tra độ dài chuỗi
        if (phoneNumber.length() != 10) {
            return false;
        }

        // Kiểm tra ký tự đầu tiên
        if (!phoneNumber.startsWith("0")) {
            return false;
        }

        // Kiểm tra các ký tự còn lại là số
        for (char digit : phoneNumber.toCharArray()) {
            if (!Character.isDigit(digit)) {
                return false;
            }
        }

        return true;
    }
}
