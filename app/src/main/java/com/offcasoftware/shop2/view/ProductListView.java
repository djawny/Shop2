package com.offcasoftware.shop2.view;

import com.offcasoftware.shop2.base.BaseView;
import com.offcasoftware.shop2.model.Product;

import java.util.List;

public interface ProductListView extends BaseView {

    void showNoDataInfo();

    void showErrorInfo();

    void showProducts(List<Product> products);
}
