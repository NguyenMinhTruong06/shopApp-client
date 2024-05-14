package com.example.showappclient.repository.cart;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.showappclient.localdb.AppDatabase;
import com.example.showappclient.localdb.entity.Cart;

import java.util.List;

public class CartRepositoryImpl implements CartRepository {
    private AppDatabase appDatabase;

    public CartRepositoryImpl(Application application) {
        appDatabase = AppDatabase.getInstance(application);
    }

    @Override
    public void insert(Cart cart) {
        Cart cartExist = appDatabase.cartDao().findByName(cart.getProductName());
        if (cartExist == null) {
            appDatabase.cartDao().insert(cart);
        } else {
            cartExist.setQuantity(cartExist.getQuantity() + 1);
            appDatabase.cartDao().update(cartExist);
        }
    }

    @Override
    public List<Cart> findAll() {
        return appDatabase.cartDao().findAll();
    }


}
