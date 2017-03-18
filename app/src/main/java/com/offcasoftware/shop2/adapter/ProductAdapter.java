package com.offcasoftware.shop2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.view.widget.ProductCardView;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter<Product> implements ProductCardView.ProductCardViewInterface {

    private ProductCardView.ProductCardViewInterface mListener;

    public ProductAdapter(Context context, ProductCardView.ProductCardViewInterface listener) {
        this(context, null, listener);
    }

    public ProductAdapter(Context context, List<Product> list, ProductCardView.ProductCardViewInterface mListener) {
        super(context, list);
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        //LayoutInflater inflater = LayoutInflater.from(context);
        final ProductCardView view = new ProductCardView(context);
        return new ProductHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, Product item, int position) {
        ((ProductHolder) holder).bind(item);
    }

    @Override
    public void onProductClicked(Product product) {
        if (mListener != null) {
            mListener.onProductClicked(product);
        }
    }

    public class ProductHolder extends RecyclerView.ViewHolder {

        public ProductHolder(View itemView) {
            super(itemView);
        }

        public void bind(Product product) {
            ((ProductCardView) itemView).bindTo(product, ProductAdapter.this);
        }
    }
}
