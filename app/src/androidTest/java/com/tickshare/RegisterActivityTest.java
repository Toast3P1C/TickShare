package com.tickshare;


import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.activities.MainActivity;
import com.activities.RegisterActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

public class RegisterActivityTest {
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
    public void initString() {
        name = "Paul";
        lastName = "Tester";
        region = "Berlin";
        emailAddress = "tester@test.test";
        password = "strongPassword";
    }

    @Test
    public void createNewUserUseCaseTest() {
        Espresso.onView(withId(R.id.buttonCreateAnAccount)).perform(click());
        Espresso.onView(withId(R.id.inputTextCreateAccountName)).perform(typeText(name), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountLastName)).perform(typeText(lastName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountRegion)).perform(typeText(region), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountEmailAddress)).perform(typeText(emailAddress), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountPassword)).perform(typeText(password), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountConfirmPassword)).perform(typeText(password), closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonCreateAccountNext)).perform(click());
        assertTrue(registerActivityActivityTestRule.getActivity().isFinishing());

    }

    @Test
    public void createNewUserFieldIsMissingAndShouldShowErrorTest() {
        Espresso.onView(withId(R.id.buttonCreateAnAccount)).perform(click());
        Espresso.onView(withId(R.id.inputTextCreateAccountName)).perform(typeText(name), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountLastName)).perform(typeText(lastName), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountRegion)).perform(typeText(""), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountEmailAddress)).perform(typeText(emailAddress), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountPassword)).perform(typeText(password), closeSoftKeyboard());
        Espresso.onView(withId(R.id.inputTextCreateAccountConfirmPassword)).perform(typeText(password), closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonCreateAccountNext)).perform(click());
        assertTrue(registerActivityActivityTestRule.getActivity().getAlertDialog().isShowing());
    }


}
