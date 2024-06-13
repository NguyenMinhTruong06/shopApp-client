package com.example.showappclient.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showappclient.R;
import com.example.showappclient.model.response.OrderResponse;


import java.util.ArrayList;
import java.util.List;

public class OrderListHistoryAdapter extends RecyclerView.Adapter<OrderListHistoryAdapter.ViewHolder> {
    private List<OrderResponse> orders = new ArrayList<>();
    private static OnItemClickListener onItemClickListener;


    public void setOrders(List<OrderResponse> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    public OrderResponse getOrder(int position) {
        return orders.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_history, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderResponse order = orders.get(position);
        if (order != null) {
            holder.bind(order);
        }
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOrderId, tvPrice, tvStatus, tvPaymentMethod;


        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            tvOrderId = itemView.findViewById(R.id.text_id_order);
            tvPrice = itemView.findViewById(R.id.text_price);
            tvStatus = itemView.findViewById(R.id.text_status);
            tvPaymentMethod = itemView.findViewById(R.id.text_number_pro);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }


        public void bind(OrderResponse order) {
            tvOrderId.setText("Mã đơn hàng: "+String.valueOf(order.getId()));
            tvPrice.setText("Tổng tiền: "+String.valueOf(order.getTotalMoney()));
            tvStatus.setText("Trạng thái: "+order.getStatus());
            tvPaymentMethod.setText("Phương thức thanh toán: "+order.getPaymentMethod());
        }

    }

}
