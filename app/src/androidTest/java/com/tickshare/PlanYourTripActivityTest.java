package com.tickshare;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.activities.MainActivity;
import com.activities.OfferATripActivity;
import com.activities.PlanYourTripActivity;
import com.authentication.Constants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class PlanYourTripActivityTest {

    private String startingLocation;
    private String destination;
    private String startingTime;
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public ActivityTestRule<PlanYourTripActivity> registerActivityActivityTestRule = new ActivityTestRule<>(PlanYourTripActivity.class);

    @Before
    public void initStrings(){
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date date = new Date(millis);
        startingLocation = "Berlin";
        destination = "Hamburg";
        startingTime = simpleDateFormat.format(date);
    }
    @Test
    public void planTripViewTest(){
        Espresso.onView(withId(R.id.buttonPlanYourTrip)).perform(click());
        Espresso.onView(withId(R.id.editTextWhereToGo)).perform(typeText(startingLocation));
        Espresso.onView(withId(R.id.editTextWhereDoYouWantToStart)).perform(typeText(destination));
        Espresso.onView(withId(R.id.editTextWhenDoYouWantToGo)).perform(typeText(startingTime));


    }

}
