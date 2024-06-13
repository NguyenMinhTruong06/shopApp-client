package com.example.showappclient.ui.home;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private int visibleThreshold = 5;
    private boolean isLoading = false;
    private int previousTotalItemCount = 0;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int totalItemCount = layoutManager.getItemCount();
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

        if (!isLoading && totalItemCount <= (lastVisibleItemPosition + visibleThreshold)) {
            onLoadMore();
            isLoading = true;
        }
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void resetState() {
        isLoading = false;
        previousTotalItemCount = 0;
    }

    public abstract void onLoadMore();
}

