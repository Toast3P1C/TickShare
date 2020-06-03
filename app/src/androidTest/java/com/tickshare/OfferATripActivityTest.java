package com.tickshare;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import com.activities.MainActivity;
import com.activities.OfferATripActivity;
import com.authentication.Constants;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class OfferATripActivityTest {

    private String startingLocation;
    private String destination;
    private String startingTime;
    private String seatsLeft;
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public ActivityTestRule<OfferATripActivity> registerActivityActivityTestRule = new ActivityTestRule<>(OfferATripActivity.class);

    @Before
    public void initStrings(){
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date date = new Date(millis);
        startingLocation = "Berlin";
        destination = "Hamburg";
        startingTime = simpleDateFormat.format(date);
        seatsLeft = "4";
    }
    @Test
    public void offerTripTest(){
        Espresso.onView(withId(R.id.buttonOfferATicket)).perform(click());
        Espresso.onView(withId(R.id.editTextWhereDouYouStart)).perform(typeText(startingLocation));
        Espresso.onView(withId(R.id.editTextWhereDoYouWantToGo)).perform(typeText(destination));
        Espresso.onView(withId(R.id.editTextWhenDoYOuStart)).perform(typeText(startingTime));
        Espresso.onView(withId(R.id.editTextHowManySeatsLeft)).perform(typeText(seatsLeft));

    }


}
