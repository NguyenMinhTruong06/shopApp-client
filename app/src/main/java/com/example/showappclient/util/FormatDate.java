package com.example.showappclient.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDate {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String formatDate(String str) {
        LocalDate strNew = LocalDate.parse(str.substring(0, 10));
        return strNew.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String formatDateOfBirth(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return outputFormatter.format(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String formatDateOfBirthView(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return outputFormatter.format(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String formatDateReverse(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return outputFormatter.format(date);
    }
}
