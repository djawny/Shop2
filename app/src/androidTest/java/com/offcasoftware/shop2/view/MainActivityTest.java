package com.offcasoftware.shop2.view;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.offcasoftware.shop2.R;
import com.offcasoftware.shop2.view.widget.ProductCardView;
import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private Solo mSolo;

    @Before
    public void setUp() throws Exception {
        mSolo = new Solo(InstrumentationRegistry.getInstrumentation(), mActivityTestRule.getActivity());
        mSolo.unlockScreen();
    }

    @After
    public void tearDown() throws Exception {
        mSolo.finishOpenedActivities();
    }

    @Test
    public void testMainActivityWasOpened() {
        mSolo.assertCurrentActivity("MainActivity", MainActivity.class);
    }

    @Test
    public void testMainActivityList() {
        View view = mSolo.getView(R.id.product_recycler);
        assertNotNull(view);

        final RecyclerView recyclerView = (RecyclerView) view;

        ProductCardView productCardView = (ProductCardView) recyclerView.findViewHolderForAdapterPosition(0).itemView;
        TextView productName = (TextView) productCardView.findViewById(R.id.product_name);

        assertEquals("dom 1", productName.getText());
    }
}