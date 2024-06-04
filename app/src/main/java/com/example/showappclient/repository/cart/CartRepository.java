package com.example.showappclient.repository.cart;

import androidx.lifecycle.LiveData;

import com.example.showappclient.localdb.entity.Cart;

import java.util.List;

public interface CartRepository {
    void insert(Cart cart);
    List<Cart> findAll();

    void delete(Cart cart);
}
