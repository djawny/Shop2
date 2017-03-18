package com.offcasoftware.shop2.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepository;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

public class GetProductDetails extends AsyncTaskLoader<Product> {

    public static final String PRODUCT_KEY = GetProductDetails.class.getCanonicalName() + "_PRODUCT_KEY";

    private final int mProductId;

    private ProductRepositoryInterface mProductRepository = ProductRepository.getInstance();

    public GetProductDetails(Context context, int productId) {
        super(context);
        mProductId = productId;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Product loadInBackground() {
        final Product product = mProductId != Product.UNDEFINED ? mProductRepository.getProduct(mProductId) : null;
        return product;
    }
}
