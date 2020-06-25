package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.authentication.Constants;
import com.model.ITrip;
import com.tickshare.R;


public class ShowSingleTripActivity extends AppCompatActivity {

    ITrip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsingletrip);
        fillTextView();
    }

    private void fillTextView() {
        TextView textView = findViewById(R.id.textViewShowSingleTripInformation);
        trip = MainActivity.tripManager.getTripList().get(ShowTripsActivity.position);
        textView.setText(trip.toString());
    }

    public void onButtonBackClick(View view) {
        Intent intent = new Intent(this, ShowTripsActivity.class);
        startActivity(intent);
        finish();
    }

    public void onButtonAcceptClick(View view) {
        int seatsLeft = Integer.parseInt(trip.getSeatsLeft());
        seatsLeft--;
        trip.setSeatsLeft(String.valueOf(seatsLeft));
        MainActivity.tripManager.updateTrip(Constants.BASE_URL, trip.getId(), trip.getStartingLocation(),
                trip.getDestination(), trip.getStartingTime(), trip.getSeatsLeft());
        seatsLeft = 0;
    }

}
