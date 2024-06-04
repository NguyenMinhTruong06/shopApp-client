package com.example.showappclient.repository.category;

import com.example.showappclient.api.RetrofitClient;
import com.example.showappclient.model.Category;
import com.example.showappclient.model.response.CategoryResponse;

import java.util.List;

import retrofit2.Call;

public class CategoryReponsitoryImpl implements CategoryReponsitory{
    @Override
    public Call<List<Category>> getCategory() {
        return RetrofitClient.getApiService().getCategory();
    }
}
