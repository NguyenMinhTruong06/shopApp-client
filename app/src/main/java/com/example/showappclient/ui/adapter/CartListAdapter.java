package com.example.showappclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.showappclient.R;
import com.example.showappclient.localdb.AppDatabase;
import com.example.showappclient.localdb.entity.Cart;


import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartViewHolder>  {

    private List<Cart> productList = new ArrayList<>();
    private AppDatabase appDatabase;
    private Cart currentProduct;
    private static OnItemClickListener onItemClickListener;

    public CartListAdapter(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }


    public Cart getProduct(int position) {
        return productList.get(position);
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product_list, parent, false);
        return new CartViewHolder(view,appDatabase);
    }


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart product = productList.get(position);
        holder.bind(product);

    }
    public void setData(List<Cart> carts ) {
        this.productList = carts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    }

    public void add(Cart product) {
    }

    public void remove(Cart product) {
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        private CheckBox checkBox;
        private TextView tvName, tvPrice,tvAdd,tvRemove,tvValue,tvId;
        private ImageView imgProduct;
        private Cart currentProduct;
        private AppDatabase appDatabase;
        public CartViewHolder(@NonNull View itemView,AppDatabase appDatabase){
            super(itemView);
            this.appDatabase = appDatabase;
            checkBox = itemView.findViewById(R.id.checkbox_favorite);
            tvName = itemView.findViewById(R.id.text_name_product);
            tvPrice = itemView.findViewById(R.id.text_price_product);
            tvAdd = itemView.findViewById(R.id.text_add);
            tvRemove = itemView.findViewById(R.id.text_remove);
            tvValue = itemView.findViewById(R.id.text_quantity_value);
            imgProduct = itemView.findViewById(R.id.image_product);
            tvId = itemView.findViewById(R.id.text_description);

            tvAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(position);
                        }
                        add(currentProduct);
                    }
                }
            });
            tvRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(position);
                        }
                        remove(currentProduct);
                    }
                }
            });



        }

        public void bind (Cart product){
            currentProduct = product;
            tvName.setText(product.getProductName());
            tvPrice.setText(String.valueOf(product.getPrice()));
            tvValue.setText(String.valueOf(product.getQuantity()));
            Glide.with(itemView.getRootView())
                    .load(product.getImagePath())
                    .centerCrop()
                    .into(imgProduct);

        }
        public void add(Cart product) {
            int quantity = product.getQuantity() + 1;
            product.setQuantity(quantity);
            tvValue.setText(String.valueOf(quantity));
            appDatabase.cartDao().update(product);

        }
        public void remove(Cart product) {
            int quantity = product.getQuantity() -1;
            if (quantity == 0) {
                appDatabase.cartDao().delete(product);
                appDatabase.cartDao().update(product);
            } else {
                product.setQuantity(quantity);
                tvValue.setText(String.valueOf(quantity));
                appDatabase.cartDao().update(product);
            }

        }
    }

}
