package com.example.showappclient.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showappclient.R;

import com.example.showappclient.model.OrderDetail;
import com.example.showappclient.model.response.OrderDetailResponse;



import java.util.ArrayList;
import java.util.List;

public class OrderProductListHistoryAdapter extends RecyclerView.Adapter<OrderProductListHistoryAdapter.ViewHolder>  {
    private List<OrderDetail> orders = new ArrayList<>();

    public OrderProductListHistoryAdapter(List<OrderDetail> orderList) {
        this.orders = orderList;
    }

    public void setOrders(List<OrderDetail> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }
    public OrderDetail getOrder(int position) {
        return orders.get(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list_order_history, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return orders.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail order = orders.get(position);
        if (order != null) {
            holder.bind(order);
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvOption, tvPrice, tvQuantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_name_product);
            tvOption = itemView.findViewById(R.id.text_option);
            tvPrice = itemView.findViewById(R.id.text_price);
            tvQuantity = itemView.findViewById(R.id.text_number_pro);
        }

        public void bind(OrderDetail orderDetail) {
            tvName.setText("Tên sản phẩm: "+orderDetail.getProductName());
            tvPrice.setText("Giá tiền: "+orderDetail.getPrice());
            tvOption.setText("Cấu hình: "+orderDetail.getOption());
            tvQuantity.setText("Số lượng: "+orderDetail.getNumberOfProducts());
        }
    }

}
