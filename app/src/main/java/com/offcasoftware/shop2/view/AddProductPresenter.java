package com.offcasoftware.shop2.view;

import com.offcasoftware.shop2.base.BasePresenter;
import com.offcasoftware.shop2.model.Product;
import com.offcasoftware.shop2.repository.ProductRepositoryInterface;
import com.offcasoftware.shop2.util.Precondition;

public class AddProductPresenter extends BasePresenter<AddProductView> {
    private ProductRepositoryInterface mProductRepositoryInterface;

    public AddProductPresenter(ProductRepositoryInterface productRepositoryInterface) {
        mProductRepositoryInterface = Precondition.checkNotNull(productRepositoryInterface);
    }

    public void addProduct(String name, String price) {
        int priceInt = Integer.parseInt(price);
        Product product = new Product(name, priceInt);
        try {
            mProductRepositoryInterface.addProduct(product);
            getView().closeScreen();
        } catch (Exception e) {
            getView().showError();
        }
    }
}
