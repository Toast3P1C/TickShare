package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tickshare.R;


public class ShowSingleTripActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showsingletrip);
        fillTextView();
    }

    private void fillTextView(){
       TextView textView = findViewById(R.id.textViewShowSingleTripInformation);
        textView.setText(MainActivity.tripManager.getTripList().get(ShowTripsActivity.position).toString());
    }
    public void onButtonBackClick(View view){
        Intent intent = new Intent(this, ShowTripsActivity.class);
        startActivity(intent);
        finish();
    }

}
