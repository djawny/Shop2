package com.offcasoftware.shop2.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import java.util.List;

public class GetAllProducts extends AsyncTaskLoader<List<Product>> {

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();

    public GetAllProducts(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Product> loadInBackground() {
        final List<Product> productList = mProductRepository.getProducts();
        return productList;
    }
}
