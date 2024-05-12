package com.example.showappclient.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.showappclient.model.Product;
import com.example.showappclient.model.response.ProductResponse;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<String> jsonStringLiveData = new MutableLiveData<>();

    public LiveData<String> getJsonStringLiveData() {

        return jsonStringLiveData;
    }

}
