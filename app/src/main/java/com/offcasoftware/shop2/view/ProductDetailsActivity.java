package com.offcasoftware.shop2.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.offcasoftware.shop2.R;

public class ProductDetailsActivity extends AppCompatActivity {

    public static final String INTENT_PRODUCT_ID =
            ProductDetailsActivity.class.getSimpleName() + "productId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        final int id = getIntent().getExtras().getInt(INTENT_PRODUCT_ID);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.product_details_container, ProductDetailsFragment.newInstance(id), null)
                .commit();
    }

}
