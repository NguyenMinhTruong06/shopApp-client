package com.example.showappclient.ui.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.showappclient.R;
import com.example.showappclient.model.ProductOption;

import java.util.ArrayList;
import java.util.List;

public class OptionListAdapter extends RecyclerView.Adapter<OptionListAdapter.ViewHolder> {
    private List<ProductOption> options = new ArrayList<>();
    private static OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductOption productOption = options.get(position);
        holder.bind(productOption);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public void setOptions(List<ProductOption> options) {
        this.options = options;
        notifyDataSetChanged();
    }
    public ProductOption getOptions(int position){
        return options.get(position);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView optionNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            optionNameTextView = itemView.findViewById(R.id.text_option_name);
        }

        public void bind(ProductOption option) {
            optionNameTextView.setText(option.getOption());

            optionNameTextView.setOnClickListener(new View.OnClickListener() {
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
