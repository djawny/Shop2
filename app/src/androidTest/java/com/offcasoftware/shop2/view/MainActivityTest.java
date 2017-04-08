package com.offcasoftware.shop2.view;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
    public void testAddProductActivityWasOpened() {
        mSolo.assertCurrentActivity("MainActivity", MainActivity.class);
    }



}