package com.tickshare;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import com.activities.MainActivity;
import com.activities.OfferATripActivity;
import com.activities.PlanYourTripActivity;
import com.activities.RegisterActivity;
import com.activities.UserLoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.tickshare", appContext.getPackageName());
    }
    @Test
    public void onCreateNewUserShouldOpenNewViewTest(){
        Espresso.onView(withId(R.id.buttonCreateAnAccount)).perform(click());
        intended(hasComponent(RegisterActivity.class.getName()));
    }

    @Test
    public void onPlanTripShouldOpenNewViewTest(){
        Espresso.onView(withId(R.id.buttonPlanYourTrip)).perform(click());
        intended(hasComponent(PlanYourTripActivity.class.getName()));
    }
    @Test
    public void onOfferTripShouldOpenNewViewTest(){
        Espresso.onView(withId(R.id.buttonOfferATicket)).perform(click());
        intended(hasComponent(OfferATripActivity.class.getName()));
    }
    @Test
    public void onAlreadyRegisteredShouldOpenNewViewTest(){
        Espresso.onView(withId(R.id.buttonAlreadyRegistred)).perform(click());
        intended(hasComponent(UserLoginActivity.class.getName()));
    }


}
