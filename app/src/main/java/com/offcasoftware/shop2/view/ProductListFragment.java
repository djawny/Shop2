package com.offcasoftware.shop2.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.adapter.ProductAdapter;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;
import com.offcasoftware.shop2.view.widget.ProductCardView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListFragment extends Fragment implements ProductCardView.ProductCardViewInterface {

    @BindView(R.id.product_recycler)
    RecyclerView mRecyclerView;

    public interface OnProductSelected {
        void onProductSelected(Product product);

    }

    private ProductAdapter mProductAdapter;
    private List<Product> products;
    private OnProductSelected mListener;

    private ProductRepositoryInterface mProductRepository
            = ProductRepository.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayData();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnProductSelected) {
            mListener = (OnProductSelected) activity;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        products = mProductRepository.getProducts();
        mProductAdapter.swapData(products);
    }

    private void displayData() {
        products = mProductRepository.getProducts();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProductAdapter = new ProductAdapter(products, this);
        mRecyclerView.setAdapter(mProductAdapter);
    }

    @Override
    public void onProductClicked(Product product) {
        if (mListener != null) {
            mListener.onProductSelected(product);
        }
    }
}