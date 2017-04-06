package com.offcasoftware.shop2.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.repository.ProductRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddProductActivity extends AppCompatActivity implements AddProductView {

    @BindView(R.id.product_name)
    EditText mProductName;

    @BindView(R.id.product_price)
    EditText mProductPrice;

    private AddProductPresenter mAddProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
        mAddProductPresenter = new AddProductPresenter(ProductRepository.getInstance());
        mAddProductPresenter.setView(this);
    }

    @OnClick(R.id.button_add_product)
    public void onAddProductClicked(View view) {
        String name = mProductName.getText().toString().trim();
        String price = mProductPrice.getText().toString().trim();
        mAddProductPresenter.addProduct(name, price);

    }

    @Override
    public void closeScreen() {
        onBackPressed();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAddProductPresenter.clearCompositeDisposable();
    }
}
