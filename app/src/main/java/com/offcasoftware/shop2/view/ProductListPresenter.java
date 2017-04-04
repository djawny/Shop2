package com.offcasoftware.shop2.view;

import com.offcasoftware.shop2.base.BasePresenter;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;

import java.util.List;

public class ProductListPresenter extends BasePresenter<ProductListView> {

    private final ProductRepositoryInterface mProductRepository;

    public ProductListPresenter(ProductRepositoryInterface repository) {
        mProductRepository = repository;
    }

    public void loadProducts() {
        List<Product> list = mProductRepository.getProducts();
        getView().showProducts(list);
    }
}
