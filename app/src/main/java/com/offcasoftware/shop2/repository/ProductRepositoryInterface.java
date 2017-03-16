package com.offcasoftware.shop2.repository;

import android.support.annotation.NonNull;

import com.offcasoftware.shop2.model.Product;

import java.util.List;

public interface ProductRepositoryInterface {

    @NonNull
    List<Product> getProducts();

    Product getProduct(int productId);

}
