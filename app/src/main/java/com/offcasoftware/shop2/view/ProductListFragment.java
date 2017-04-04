package com.offcasoftware.shop2.view;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.adapter.ProductAdapter;
import com.offcasoftware.shop2.loader.GetAllProducts;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.provider.ProductProvider;
import com.offcasoftware.shop2.view.widget.ProductCardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListFragment extends Fragment
        implements ProductCardView.ProductCardViewInterface, LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.product_recycler)
    RecyclerView mRecyclerView;

    private ProductAdapter mAdapter;
    private OnProductSelected mListener;

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return new GetAllProducts(getActivity());
        return new CursorLoader(getActivity(), ProductProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        final List<Product> products = getProducts(cursor);
        if (mListener != null) {
            mListener.onProductReady(products);
        }
        mAdapter.swapData(products);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.clearData();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ProductAdapter(getActivity(), this);
        mRecyclerView.setAdapter(mAdapter);
        getLoaderManager().initLoader(1, null, this);
    }

    public interface OnProductSelected {
        void onProductReady(List<Product> products);

        void onProductSelected(Product product);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnProductSelected) {
            mListener = (OnProductSelected) activity;
        }
    }

    @Override
    public void onProductClicked(Product product) {
        if (mListener != null) {
            mListener.onProductSelected(product);
        }
    }

    private List<Product> getProducts(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return Collections.emptyList();
        }
        final List<Product> products = new ArrayList<>();
        cursor.moveToFirst();
        do {
            final Product product = new Product(cursor);
            products.add(product);
        } while (cursor.moveToNext());
        cursor.close();
        return products;
    }
}
