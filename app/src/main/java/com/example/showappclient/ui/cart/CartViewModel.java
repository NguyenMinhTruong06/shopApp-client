package com.example.showappclient.ui.cart;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.showappclient.localdb.AppDatabase;
import com.example.showappclient.localdb.dao.CartDao;
import com.example.showappclient.localdb.entity.Cart;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.response.ProductResponse;
import com.example.showappclient.repository.cart.CartRepository;
import com.example.showappclient.repository.cart.CartRepositoryImpl;

import java.util.List;



public class CartViewModel extends AndroidViewModel {
    CartRepository cartRepository;
    private CartDao cartDao;
    public MutableLiveData<List<Cart>> products = new MutableLiveData<>();

    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepository = new CartRepositoryImpl(application);

    }

    public void addToCart(Cart cart){

        cartRepository.insert(cart);
    }

    public void getAllProductInCart() {
        products.postValue(cartRepository.findAll());
    }
    public void deleteFromCart(Cart cart) {
        cartRepository.delete(cart);

    }
}
