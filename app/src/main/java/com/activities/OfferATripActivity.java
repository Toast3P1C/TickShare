package com.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tickshare.R;

public class OfferATripActivity extends AppCompatActivity {
    private EditText startingLocation, destination, startingTime,
            seatsLeft;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offertrip);
    }

    public void onOfferNow(View view){

    }

    private void initaliseEditTexts(){
        startingLocation = findViewById(R.id.editTextWhereDouYouStart);
        destination = findViewById(R.id.editTextWhereDoYouWantToGo);
        startingTime = findViewById(R.id.editTextWhenDoYOuStart);
        seatsLeft = findViewById(R.id.editTextHowManySeatsLeft);

    }
}
