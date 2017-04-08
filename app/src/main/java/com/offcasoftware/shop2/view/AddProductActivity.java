package com.offcasoftware.shop2.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.repository.ProductRepository;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddProductActivity extends AppCompatActivity implements AddProductView {

    @BindView(R.id.product_name)
    EditText mProductName;

    @BindView(R.id.product_price)
    EditText mProductPrice;

    @BindView(R.id.button_add_product)
    TextView mAddProductButton;

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            final String name = mProductName.getText().toString();
            final String price = mProductPrice.getText().toString();
            mAddProductPresenter.onTextChanged(name, price);
        }
    };

    private AddProductPresenter mAddProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);

        mProductName.addTextChangedListener(textWatcher);
        mProductPrice.addTextChangedListener(textWatcher);

        mAddProductPresenter = new AddProductPresenter(ProductRepository.getInstance(),
                Schedulers.io(), AndroidSchedulers.mainThread());
        mAddProductPresenter.setView(this);
        mAddProductPresenter.setUpView();
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
    public void enableAddButton(boolean enabled) {
        mAddProductButton.setEnabled(enabled);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAddProductPresenter.clearDisposible();
    }
}
