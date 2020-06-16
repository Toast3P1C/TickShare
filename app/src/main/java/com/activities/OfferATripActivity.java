package com.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.authentication.Constants;
import com.model.ITrip;
import com.tickshare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OfferATripActivity extends AppCompatActivity {
    private EditText startingLocation, destination, startingTime,
            seatsLeft;
    private AlertDialog dialog;
    final Calendar calender = Calendar.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offertrip);
        initialiseEditTexts();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void onOfferNow(View view) {
        String userToken = "";
        if (MainActivity.userManager.getUserList().size() > 0) {
            userToken = MainActivity.userManager.getUserList().get(0).getToken();
        }
        String startingLocationString = startingLocation.getEditableText().toString().trim();
        String destinationString = destination.getEditableText().toString().trim();
        String startingTimeString = startingTime.getEditableText().toString().trim();
        String seatsLeftString = seatsLeft.getEditableText().toString().trim();

        if (userToken != "" && !userToken.isEmpty() && userToken != null) {
            if (MainActivity.tripManager.createTripWithUserToken(startingLocationString, destinationString,
                    startingTimeString, seatsLeftString, MainActivity.userManager.getUserList().get(0).getToken())) {
                MainActivity.tripManager.sendTripToServer(Constants.BASE_URL+"/trip",MainActivity.tripManager.getTripList().get(0));
                MainActivity.tripManager.getTripList().clear();
                showSuccessAlert();
                finish();
            } else {
                showErrorAlert();
                return;
            }
        } else {
            if (MainActivity.tripManager.createTripWithoutUserToken(startingLocationString, destinationString,
                    startingTimeString, seatsLeftString)) {
                MainActivity.tripManager.sendTripToServer(Constants.BASE_URL+"/trip",MainActivity.tripManager.getTripList().get(0));
                MainActivity.tripManager.getTripList().clear();
                showSuccessAlert();
                finish();
            } else {
                showErrorAlert();
                return;
            }
        }

    }

    public void onDateNowClick(View view) {
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date date = new Date(millis);
        startingTime.setText(simpleDateFormat.format(date));
    }

    public void onDateClick(View view) {
        new DatePickerDialog(OfferATripActivity.this,datePickerDialog,
                calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void onTimeClick(View view){
        new TimePickerDialog(OfferATripActivity.this,
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


    private void initialiseEditTexts() {
        startingLocation = findViewById(R.id.editTextWhereDouYouStart);
        destination = findViewById(R.id.editTextWhereDoYouWantToGo);
        startingTime = findViewById(R.id.editTextWhenDoYOuStart);
        seatsLeft = findViewById(R.id.editTextHowManySeatsLeft);
    }

    private void showSuccessAlert() {
        Toast.makeText(getApplicationContext(), Constants.TRIP_SUCCESS, Toast.LENGTH_LONG).show();
    }

    private void showErrorAlert() {
        dialog = new AlertDialog.Builder(this)
                .setTitle("Register")
                .setMessage("Could not create your account please check your entries: " + MainActivity.tripManager.getErrorMap().values().toString())
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public AlertDialog getAlertDialog() {
        return dialog;
    }
}
