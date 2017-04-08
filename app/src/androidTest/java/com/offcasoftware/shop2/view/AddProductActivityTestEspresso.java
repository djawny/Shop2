package com.offcasoftware.shop2.view;

import android.support.test.rule.ActivityTestRule;

import com.offcasoftware.shop2.R;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;

public class AddProductActivityTestEspresso {

    @Rule
    public ActivityTestRule<AddProductActivity> mActivityTestRule = new ActivityTestRule<>(AddProductActivity.class);

    @Test
    public void testAddProductIsDisplayed() {
        onView(withId(R.id.product_name)).check(matches(isDisplayed()));
        onView(withId(R.id.product_price)).check(matches(isDisplayed()));

        onView(withId(R.id.product_name)).perform(typeText("Product name"));
        onView(withId(R.id.product_price)).perform(typeText("1"),closeSoftKeyboard());

        onView(withId(R.id.button_add_product)).perform(click());
    }
}