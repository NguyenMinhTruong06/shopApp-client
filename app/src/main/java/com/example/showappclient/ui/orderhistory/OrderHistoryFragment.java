package com.example.showappclient.ui.orderhistory;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

import com.example.showappclient.R;
import com.example.showappclient.model.Order;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.request.OrderRequest;
import com.example.showappclient.model.response.OrderResponse;
import com.example.showappclient.ui.adapter.OnItemClickListener;
import com.example.showappclient.ui.adapter.OrderListHistoryAdapter;
import com.example.showappclient.ui.orderdetailhistory.OrderDetailHistoryFragment;
import com.example.showappclient.ui.product.ProductViewModel;

import java.io.Serializable;
import java.util.List;

public class OrderHistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private OrderListHistoryAdapter orderListHistoryAdapter;

    private OrderHistoryViewModel mViewModel;
    private ImageView imgLeft;
    Long userId = 0l;

    public static OrderHistoryFragment newInstance() {
        return new OrderHistoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory()).get(OrderHistoryViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_history, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgLeft = view.findViewById(R.id.image_left_order);
//        mViewModel = new OrderHistoryViewModel();
        orderListHistoryAdapter = new OrderListHistoryAdapter();
//        OrderListHistoryAdapter adapter = new OrderListHistoryAdapter();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_order_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(orderListHistoryAdapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
           userId = Long.valueOf(bundle.getInt("userId"));
        }
        mViewModel.orderLiveData.observe(getViewLifecycleOwner(), new Observer<List<OrderResponse>>() {
            @Override
            public void onChanged(List<OrderResponse> orderList) {
                if (orderList != null) {
                    orderListHistoryAdapter.setOrders(orderList); // Update adapter data
                } else {

                }
            }
        });
        mViewModel.getOrder(userId);


        imgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
        orderListHistoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                OrderResponse order = orderListHistoryAdapter.getOrder(position);
//                Bundle bundle1 = new Bundle();
//                bundle1.putSerializable("order", order);
//                OrderDetailHistoryFragment orderDetailHistoryFragment = new OrderDetailHistoryFragment();
//                orderDetailHistoryFragment.setArguments(bundle1);
//                requireActivity().getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.root,orderDetailHistoryFragment)
//                        .addToBackStack("OrderHistory")
//                        .commit();
                long orderId = order.getId(); // Lấy ID của đơn hàng
                Bundle bundle1 = new Bundle();
                bundle1.putLong("orderId", orderId); // Đặt ID vào Bundle
                OrderDetailHistoryFragment orderDetailHistoryFragment = new OrderDetailHistoryFragment();
                orderDetailHistoryFragment.setArguments(bundle1);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root, orderDetailHistoryFragment)
                        .addToBackStack("OrderHistory")
                        .commit();

            }
        });



    }
}