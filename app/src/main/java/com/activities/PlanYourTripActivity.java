package com.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.authentication.Constants;
import com.model.ITrip;
import com.tickshare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlanYourTripActivity extends AppCompatActivity {

    private EditText startingLocation, destination, startingTime;
    final Calendar calender = Calendar.getInstance();

    private List<ITrip> tripList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantrip);
        initialiseEditTexts();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public void onButtonSearchClick(View view){
        this.tripList = MainActivity.networkManager.get(Constants.BASE_URL+"/trips");
        String startingLocation = this.startingLocation.getEditableText().toString().trim();
        String destination = this.destination.getEditableText().toString().trim();

        //TODO: Add startingtime check when searching for trips
        String startingTime = this.startingTime.getEditableText().toString().trim();


        for(ITrip trip : this.tripList){
            if(trip.getStartingLocation().equals(startingLocation) && trip.getDestination().equals(destination)
                    && Integer.valueOf(trip.getSeatsLeft()) > 0){
               MainActivity.tripManager.getTripList().add(trip);
            }
        }
        Intent intent = new Intent(this,ShowTripsActivity.class);
        startActivity(intent);
    }


    public void onDateNowClick(View view) {
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date date = new Date(millis);
        startingTime.setText(simpleDateFormat.format(date));
    }

    public void onDateClick(View view) {
        new DatePickerDialog(PlanYourTripActivity.this,datePickerDialog,
                calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void onTimeClick(View view){
        new TimePickerDialog(PlanYourTripActivity.this,
                timeSetListener,
                calender.get(Calendar.HOUR_OF_DAY),
                calender.get(Calendar.MINUTE),true).show();
    }

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calender.set(Calendar.HOUR_OF_DAY,hourOfDay);
            calender.set(Calendar.MINUTE,minute);
            updateLabelDate();
        }
    };

    DatePickerDialog.OnDateSetListener datePickerDialog = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calender.set(Calendar.YEAR, year);
            calender.set(Calendar.MONTH, month);
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabelDate();
        }
    };
    private void updateLabelDate(){
        String myFormat = Constants.DATE_FORMAT;
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        startingTime.setText(sdf.format(calender.getTime()));
    }

    private void initialiseEditTexts(){
        startingLocation = findViewById(R.id.editTextWhereDoYouWantToStart);
        destination = findViewById(R.id.editTextWhereToGo);
        startingTime = findViewById(R.id.editTextWhenDoYouWantToGo);

    }



}
