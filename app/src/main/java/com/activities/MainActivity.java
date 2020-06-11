package com.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.authentication.Constants;
import com.management.ITripManager;
import com.management.TripManager;
import com.management.UserManager;
import com.network.INetworkManager;
import com.network.NetworkManager;
import com.tickshare.R;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.text.SimpleDateFormat;
import java.util.Date;





public class MainActivity extends AppCompatActivity {
    public static UserManager userManager;
    public static INetworkManager networkManager;
    public static ITripManager tripManager;
    private final static Logger LOG = LogManager.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();
        networkManager = new NetworkManager();
        tripManager = new TripManager();

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
        long millis = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        Date date = new Date(millis);
        Date date1 = new Date(System.currentTimeMillis()+100);
        System.out.println(tripManager.createTripWithUserToken("Berlin","Hamburg",simpleDateFormat.format(date),"3","TestToken"));
        System.out.println(tripManager.getTripFromServer(1));
        System.out.println(tripManager.updateTrip(1,"Hamburg","Berlin",date1.toString(),"1"));


    }
}
