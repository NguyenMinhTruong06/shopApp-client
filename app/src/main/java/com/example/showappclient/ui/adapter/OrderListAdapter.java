package com.example.showappclient.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.showappclient.R;
import com.example.showappclient.localdb.entity.Cart;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder> {

    private List<Cart> selectedProducts;

    public OrderListAdapter(List<Cart> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_product, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Cart cart = selectedProducts.get(position);
        holder.bind(cart);
    }
    public void clear() {
        selectedProducts.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (selectedProducts != null) {
            return selectedProducts.size();
        } else {
            return 0;
        }
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPrice, tvQuantity,tvOption;
        private ImageView imgProduct;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.text_name_product);
           tvPrice = itemView.findViewById(R.id.text_price_product);
            tvQuantity = itemView.findViewById(R.id.text_quantity);
            imgProduct = itemView.findViewById(R.id.image_product);
            tvOption = itemView.findViewById(R.id.text_option);

        }

        public void bind(Cart cart) {
            tvName.setText(cart.getProductName());
            tvPrice.setText("Giá: "+(cart.getPrice())+"₫");
            tvQuantity.setText("Số lượng: "+String.valueOf(cart.getQuantity()));
            tvOption.setText(cart.getOption());
            Glide.with(itemView.getRootView())
                    .load(cart.getImagePath())
                    .centerCrop()
                    .into(imgProduct);
        }
    }
}