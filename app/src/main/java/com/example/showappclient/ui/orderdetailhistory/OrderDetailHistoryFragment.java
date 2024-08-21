package com.example.showappclient.ui.orderdetailhistory;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.showappclient.R;
import com.example.showappclient.model.Order;
import com.example.showappclient.model.OrderDetail;
import com.example.showappclient.model.response.OrderDetailResponse;
import com.example.showappclient.model.response.OrderResponse;
import com.example.showappclient.ui.adapter.OrderProductListHistoryAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailHistoryFragment extends Fragment {

    private OrderDetailHistoryViewModel mViewModel;
    private TextView tvOrderDate, tvAddress, tvPaymentMethod, tvStatus, tvShippingMethod, tvTotalPayment, tvOrderShip;

    private ImageView imgLeft;
    private RecyclerView recyclerView;
    private OrderResponse order = new OrderResponse();

    public static OrderDetailHistoryFragment newInstance() {
        return new OrderDetailHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgLeft = view.findViewById(R.id.image_left_order);
        recyclerView = view.findViewById(R.id.recycler_order_detail);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        tvAddress = view.findViewById(R.id.text_order_address);
        tvOrderDate = view.findViewById(R.id.text_order_date);
        tvOrderShip = view.findViewById(R.id.text_order_sum);
        tvPaymentMethod = view.findViewById(R.id.text_payment_method);
        tvShippingMethod = view.findViewById(R.id.text_shipping_type);
        tvStatus = view.findViewById(R.id.text_status);
        tvTotalPayment = view.findViewById(R.id.text_total_money);
        mViewModel = new ViewModelProvider(this).get(OrderDetailHistoryViewModel.class);



        //     }
        Bundle bundle = getArguments();
        if (bundle != null) {
            long orderId = bundle.getLong("orderId", 0); // Lấy ID của đơn hàng từ Bundle
            mViewModel.getOrderById(orderId);
        }
        mViewModel.getOrderLiveData().observe(getViewLifecycleOwner(), new Observer<OrderResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(OrderResponse orderResponse) {
                if (orderResponse != null) {
                    // Cập nhật các TextView tương ứng với thông tin của đơn hàng
                    tvOrderDate.setText(tvOrderDate.getText() + orderResponse.getOrderDate());
                    tvAddress.setText(tvAddress.getText() + orderResponse.getAddress());
                    tvOrderShip.setText("Ngày giao hàng: "+ orderResponse.getShippingDate());
                    tvPaymentMethod.setText(tvPaymentMethod.getText() + orderResponse.getPaymentMethod());
                    tvShippingMethod.setText(tvShippingMethod.getText() + orderResponse.getShippingMethod());
                    tvStatus.setText(tvStatus.getText() + orderResponse.getStatus());
                    tvTotalPayment.setText(String.valueOf(orderResponse.getTotalMoney())+"₫");

                    // Cập nhật RecyclerView với danh sách sản phẩm của đơn hàng
                    List<OrderDetail> orderDetails = orderResponse.getOrderDetails();
                    OrderProductListHistoryAdapter adapter = new OrderProductListHistoryAdapter(orderDetails);
                    recyclerView.setAdapter(adapter);
                } else {
                    // Xử lý trường hợp không nhận được dữ liệu đơn hàng
                }
            }
        });
        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}