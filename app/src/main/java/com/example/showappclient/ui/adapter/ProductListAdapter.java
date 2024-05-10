package com.example.showappclient.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showappclient.R;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.response.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private ProductResponse response;
    private List<Product> productList = new ArrayList<>();

    public ProductListAdapter() {

    }

    public void setData(List<Product> productList) {

        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView productDescription;
        TextView productQuantity;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.image_product);
            productName = itemView.findViewById(R.id.text_name_product);
            productPrice = itemView.findViewById(R.id.text_price_product);
            productDescription = itemView.findViewById(R.id.text_description);
            productQuantity = itemView.findViewById(R.id.text_quantity);
        }

        public void bind(Product product) {
            productName.setText(product.getName());
            productPrice.setText(String.valueOf(product.getPrice()));
            productDescription.setText(product.getDescription());

        }
    }
}
