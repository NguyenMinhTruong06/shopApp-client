package com.example.showappclient.repository.category;

import com.example.showappclient.model.Category;
import com.example.showappclient.model.response.CategoryResponse;

import java.util.List;

import retrofit2.Call;

public interface CategoryReponsitory {
    Call<List<Category>> getCategory();
}
