package com.example.showappclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.showappclient.ui.auth.login.LoginFragment;
import com.example.showappclient.ui.home.HomeFragment;
import com.example.showappclient.ui.order.OrderFragment;

import vn.zalopay.sdk.ZaloPaySDK;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("fromPayment", false)) {
            // Điều hướng đến OrderFragment nếu Intent chứa thông tin "fromPayment"
            OrderFragment orderFragment = new OrderFragment();
            transaction.replace(R.id.root, orderFragment);
        } else {
            // Mặc định điều hướng đến LoginFragment
            LoginFragment loginFragment = new LoginFragment();
            transaction.replace(R.id.root, loginFragment);

        }
        transaction.commit();
    }

    //    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//
//        // Lấy ra fragment hiện tại trên màn hình
//        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.root);
//
//        // Kiểm tra nếu fragment hiện tại là OrderFragment thì gọi phương thức handleNewIntent của nó
//        if (currentFragment instanceof OrderFragment) {
//            ((OrderFragment) currentFragment).handleNewIntent(intent);
//        }
//
//    }
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//
//        if (intent != null && intent.getBooleanExtra("fromPayment", false)) {
//            // Điều hướng đến OrderFragment khi nhận Intent mới từ thanh toán
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            OrderFragment fragment = new OrderFragment();
//            transaction.replace(R.id.root, fragment);
//            transaction.commit();
//
//            // Gửi Intent đến OrderFragment nếu cần xử lý thêm
//            fragment.handleNewIntent(intent);
//        }
//    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // Tìm fragment từ FragmentManager
        OrderFragment fragment = (OrderFragment) getSupportFragmentManager().findFragmentById(R.id.root);

        // Nếu fragment không null và intent không null, gọi phương thức handleNewIntent trên fragment
        if (fragment != null && intent != null) {
            fragment.handleNewIntent(intent);
        }
    }
}