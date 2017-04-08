package com.offcasoftware.shop2.view;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.offcasoftware.shop2.R;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AddProductActivityTest {

    private static final String PRODUCT_NAME = "dom";
    private static final String PRODUCT_PRICE = "9999";

    @Rule
    public ActivityTestRule<AddProductActivity> mActivityTestRule =
            new ActivityTestRule<>(AddProductActivity.class);

    private Solo mSolo;

    @Before
    public void setUp() {
        mSolo = new Solo(InstrumentationRegistry.getInstrumentation(), mActivityTestRule.getActivity());
        mSolo.unlockScreen();
    }

    @After
    public void tearDown() {
        mSolo.finishOpenedActivities();
    }

    @Test
    public void testAddProductActivityWasOpened() {
        mSolo.assertCurrentActivity("AddProductActivity", AddProductActivity.class);
    }

    @Test
    public void testAddProductActivityHasProperElements() {
        View productName = mSolo.getView(R.id.product_name);
        assertTrue(productName.isShown());
        View productPrice = mSolo.getView(R.id.product_price);
        assertTrue(productPrice.isShown());
        View buttonAddProduct = mSolo.getView(R.id.button_add_product);
        assertTrue(buttonAddProduct.isShown());
    }

    @Test
    public void testAddProduct() {
        EditText productName = (EditText) mSolo.getView(R.id.product_name);
        mSolo.enterText(productName,PRODUCT_NAME);
        EditText productPrice = (EditText) mSolo.getView(R.id.product_price);
        mSolo.enterText(productPrice,PRODUCT_PRICE);
        View buttonAddProduct = mSolo.getView(R.id.button_add_product);
        mSolo.clickOnView(buttonAddProduct);
    }

}