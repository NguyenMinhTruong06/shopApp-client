package com.example.showappclient.ui.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.showappclient.R;
import com.example.showappclient.model.Product;
import com.example.showappclient.model.ProductImage;
import com.example.showappclient.model.response.ProductResponse;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    private ProductResponse response;
    private List<Product> productList = new ArrayList<>();
    private static OnItemClickListener onItemClickListener;

    public ProductListAdapter() {

    }

    public void setData(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }
    public void sortByPriceDescending() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Float.compare(p2.getOptions().get(0).getPrice(), p1.getOptions().get(0).getPrice());
            }
        });
        notifyDataSetChanged();
    }
    public void sortByPriceAscending() {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Float.compare(p1.getOptions().get(0).getPrice(), p2.getOptions().get(0).getPrice());
            }
        });
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

    public Product getProduct(int position) {
        return productList.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPrice, productDescription, productQuantity;
        ConstraintLayout itemProduct;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.image_product);
            productName = itemView.findViewById(R.id.text_name_product);
            productPrice = itemView.findViewById(R.id.text_price_product);
            productDescription = itemView.findViewById(R.id.text_description);
            productQuantity = itemView.findViewById(R.id.text_quantity);
            itemProduct = itemView.findViewById(R.id.item_product);

        }

        public void bind(Product product) {
            productQuantity.setText(product.getId());
            productName.setText(product.getName());


            String imageUrl = "";
            if (product.getImages().size() > 0) {
                imageUrl = product.getImages().get(0).getImageUrl();
            }

                Glide.with(itemView.getRootView())
                        .load(imageUrl)
                        .centerCrop()
                        .into(productImage);
            Float price =0f;
            if(product.getOptions().size()>0){
                price = product.getOptions().get(0).getPrice();
            }
            productPrice.setText("Giá: "+price+"₫");
            String option="";
            if(product.getOptions().size()>0){
                option = product.getOptions().get(0).getOption();
            }
            productDescription.setText(option);
            itemProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }

    }
}
