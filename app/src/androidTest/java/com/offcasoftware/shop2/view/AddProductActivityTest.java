package com.offcasoftware.shop2.view;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddProductActivityTest {

    @Rule
    public ActivityTestRule<AddProductActivity> mActivityTestRule =
            new ActivityTestRule<>(AddProductActivity.class);

    private Solo mSolo;

    @Before
    public void setUp() {
        mSolo = new Solo(InstrumentationRegistry.getInstrumentation(), mActivityTestRule.getActivity());
    }

    @After
    public void tearDown(){
        mSolo.finishOpenedActivities();
    }


}