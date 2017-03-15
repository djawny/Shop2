package com.offcasoftware.shop2.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.offcasoftware.shop2.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductPagerActivity extends AppCompatActivity {

    @BindView(R.id.product_pager)
    ViewPager mProductPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_pager);

        ButterKnife.bind(this);
    }
}
