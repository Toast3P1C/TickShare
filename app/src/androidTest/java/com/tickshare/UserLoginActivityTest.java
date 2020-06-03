package com.tickshare;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.activities.MainActivity;
import com.activities.UserLoginActivity;
import com.management.IUserManager;
import com.management.UserManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

public class UserLoginActivityTest {
    private IUserManager userManager;
    private static String TOAST_MESSAGE = "Error during log in, please try again ! ";
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public ActivityTestRule<UserLoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(UserLoginActivity.class);

    @Before
    public void init(){
        userManager = new UserManager();
        userManager.createUser("Paul","Tester","Berlin","paul.tester@tester.de","1234567");
    }
    @Test
    public void onUserLoginTest(){
        Espresso.onView(withId(R.id.buttonAlreadyRegistred)).perform(click());
        Espresso.onView(withId(R.id.textFieldLoginEmailAddress)).perform(typeText(userManager.getUserList().get(0).getEmailAddress()), closeSoftKeyboard());
        Espresso.onView(withId(R.id.textFieldLoginPassword)).perform(typeText(userManager.getUserList().get(0).getPassword()),closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonLoginPageLogin)).perform(click());
        assertTrue(loginActivityActivityTestRule.getActivity().isFinishing());
    }
    @Test
    public void onUserLoginWrongPasswordTest(){
        Espresso.onView(withId(R.id.buttonAlreadyRegistred)).perform(click());
        Espresso.onView(withId(R.id.textFieldLoginEmailAddress)).perform(typeText(userManager.getUserList().get(0).getEmailAddress()), closeSoftKeyboard());
        Espresso.onView(withId(R.id.textFieldLoginPassword)).perform(typeText(userManager.getUserList().get(0).getPassword()),closeSoftKeyboard());
        Espresso.onView(withId(R.id.buttonLoginPageLogin)).perform(click());
        Espresso.onView(withText(TOAST_MESSAGE)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
    }

}
