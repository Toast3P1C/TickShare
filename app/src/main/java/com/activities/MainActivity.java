package com.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.management.UserManager;
import com.model.ITrip;
import com.model.Trip;
import com.network.NetworkManager;
import com.tickshare.R;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.*;



public class MainActivity extends AppCompatActivity {
    public static UserManager userManager;
    private final static Logger LOG = LogManager.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();


        //userManager.createUser("Paul","Wüsthoff","Berlin","paul.tester@test.test","test1234");

        setContentView(R.layout.activity_main);
    }
    public void onRegisterNewUser(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    public void onLogin(View view){
        Intent intent = new Intent(this,UserLoginActivity.class);
        startActivity(intent);
        test();
    }

    public void onPlanYourTrip(View view){
        Intent intent = new Intent(this,PlanYourTripActivity.class);
        startActivity(intent);
    }

    public void onOfferATrip(View view){
        Intent intent = new Intent(this,OfferATripActivity.class);
        startActivity(intent);
    }
    private void test() {
        NetworkManager.get("trip/1", null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    ITrip trip = objectMapper.readValue(response.toString(), Trip.class);
                    System.out.println(trip.toString());
                } catch (JsonProcessingException e) {
                    LOG.error("Error parsing Json", e);
                }
            }

        });

    }
}
