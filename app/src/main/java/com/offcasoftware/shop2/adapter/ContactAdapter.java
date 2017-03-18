package com.offcasoftware.shop2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ProductViewHolder> {

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        public ProductViewHolder(View itemView) {
            super(itemView);
        }
    }
}
