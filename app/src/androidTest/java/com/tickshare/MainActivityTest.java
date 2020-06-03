package com.tickshare;

import android.content.Context;
import android.widget.EditText;

import androidx.test.espresso.Espresso;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.activities.MainActivity;
import com.activities.RegisterActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    private String name;
    private String lastName;
    private String region;
    private String emailAddress;
    private String password;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public ActivityTestRule<RegisterActivity> registerActivityActivityTestRule = new ActivityTestRule<>(RegisterActivity.class);
    @Before
    public void initString(){
    name = "Paul";
    lastName = "Tester";
    region = "Berlin";
    emailAddress = "tester@test.test";
    password = "strongPassword";
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.tickshare", appContext.getPackageName());
    }
    @Test
    public void onCreateNewUserTest(){
        Espresso.onView(withId(R.id.buttonCreateAnAccount)).perform(click());
        Espresso.onView(withId(R.id.inputTextCreateAccountName)).perform(typeText(name),closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountLastName)).perform(typeText(lastName),closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountRegion)).perform(typeText(region),closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountEmailAddress)).perform(typeText(emailAddress),closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountPassword)).perform(typeText(password),closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountConfirmPassword)).perform(typeText(password),closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonCreateAccountNext)).perform(click());

    }

    @Test
    public void onLogin(){
        Espresso.onView(withId(R.id.buttonAlreadyRegistred)).perform(click());
        Espresso.onView(withId(R.id.textFieldLoginEmailAddress)).perform(typeText(emailAddress),closeSoftKeyboard());
        Espresso.onView(withId(R.id.textFieldLoginPassword)).perform(typeText(password),closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonLoginPageLogin)).perform(click());

    }



}
