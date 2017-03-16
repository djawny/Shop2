package com.offcasoftware.shop2.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.offcasoftware.shop2.model.Product;

import java.util.List;

public class GetAllProducts extends AsyncTaskLoader<List<Product>>{

    public GetAllProducts(Context context) {
        super(context);
    }

    @Override
    public List<Product> loadInBackground() {
        return null;
    }
}
