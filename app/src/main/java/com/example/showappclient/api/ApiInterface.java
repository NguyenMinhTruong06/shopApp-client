package com.example.showappclient.api;

import com.example.showappclient.model.Category;
import com.example.showappclient.model.Order;
import com.example.showappclient.model.User;
import com.example.showappclient.model.request.ChangePasswordRequest;
import com.example.showappclient.model.request.OrderDetailRequest;
import com.example.showappclient.model.request.OrderRequest;
import com.example.showappclient.model.request.UserRequest;
import com.example.showappclient.model.response.AuthResponse;

import com.example.showappclient.model.response.OrderDetailResponse;
import com.example.showappclient.model.response.OrderResponse;
import com.example.showappclient.model.response.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("/api/v1/users/login")
    Call<AuthResponse> login(@Body UserRequest loginRequest);
    @POST("/api/v1/users/register")
    Call<AuthResponse> signup(@Body UserRequest signupRequest);

    @PUT("/api/v1/users/update")
    Call<AuthResponse> update(@Body UserRequest updateRequest);

    @GET("/api/v1/products/all")
    Call<ProductResponse> getAllProduct(@Query("limit") int limit, @Query("page") int page);
    @GET("/api/v1/users/getuser")
    Call<User> getUser();

    @POST("/api/v1/users/changepassword")
    Call<AuthResponse> updatePass(@Body ChangePasswordRequest changePasswordRequest);

    @GET("/api/v1/products/all")
    Call<ProductResponse> getProductByKeywordAndCategoryId(@Query("limit") int limit, @Query("page") int page, @Query("keyword") String keyword,@Query("category_id") int categoryId);
    @GET("/api/v1/categories/get")
    Call<List<Category>> getCategory();

    @GET("/api/v1/products/${productId}")
    Call<ProductResponse>getProductById(@Query("product_id")int productId);
    @POST("/api/v1/orders")
    Call<OrderResponse>createOrder(@Body OrderRequest orderRequest);
    @POST("/api/v1/orderdetail/post")
    Call<OrderDetailResponse>createOrderDetail(@Body OrderDetailRequest orderDetailRequest);

    @GET("/api/v1/orders/user/{user_id}")

    Call<List<OrderResponse>> getOrder(@Path("user_id")Long userId);
}
