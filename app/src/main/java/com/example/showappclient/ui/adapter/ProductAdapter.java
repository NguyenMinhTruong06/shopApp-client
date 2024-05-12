package com.example.showappclient.ui.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

import com.example.showappclient.R;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.ProductImage;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<ProductImage> images=new ArrayList<>();

    public ProductAdapter() {
    }
    public void setData(List<ProductImage> images) {

        this.images = images;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductImage productImage = images.get(position);
        holder.bind(productImage);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.image_banner);

        }

        public void bind(ProductImage image) {
            Glide.with(itemView.getRootView())
                    .load(image.getImageUrl())
                    .centerCrop()
                    .into(productImage);
        }
    }
}
