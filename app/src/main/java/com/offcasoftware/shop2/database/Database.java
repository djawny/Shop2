package com.offcasoftware.shop2.database;

import com.offcasoftware.shop2.model.Product;

import java.util.List;

public interface Database {

    void saveProducts(List<Product> products);

    List<Product> getProducts();

    Product getProduct(int productId);

    void saveProduct(String name, int price);

}
