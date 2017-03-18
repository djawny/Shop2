package com.offcasoftware.shop2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    private final List<T> mItems = new ArrayList<>();
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    public BaseAdapter(Context context, List<T> list) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!!!");
        }
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        if (list != null) {
            mItems.addAll(list);
        }
    }

    public T getItem(int position) {
        final T item = mItems.get(position);
        return item;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public Context getContext() {
        return mContext;
    }

    public LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }


}
