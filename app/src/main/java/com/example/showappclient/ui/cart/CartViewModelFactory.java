package com.example.showappclient.ui.cart;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.showappclient.ui.product.ProductViewModel;

public class CartViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;

    public CartViewModelFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CartViewModel.class)) {
            return (T) new CartViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

