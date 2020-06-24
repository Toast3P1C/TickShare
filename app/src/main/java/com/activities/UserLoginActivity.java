package com.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.authentication.PasswordUtils;
import com.persistence.DBHelper;
import com.tickshare.R;

import java.util.ArrayList;

public class UserLoginActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private EditText textFieldEmail, textFieldPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textFieldEmail = findViewById(R.id.inputTextEmailAddressLogin);
        textFieldPassword = findViewById(R.id.inputTextPasswordLogin);
        dbHelper = new DBHelper(this);

//        MainActivity.userManager.createUser("Paul","Wüsthoff","Berlin","paulwpaul@web.de","1234567");
//        NetworkManager networkManager = new NetworkManager();
//        System.out.println(networkManager.get(Constants.BASE_URL+"/trips"));
//        System.out.println(MainActivity.tripManager.getTripList().get(0).toString());
//        System.out.println(networkManager.post(Constants.BASE_URL+"/trip",MainActivity.tripManager.getTripList().get(0)));
//        System.out.println(MainActivity.tripManager.getTripsFromServer(Constants.BASE_URL+"/trips").toString());
    }

    public void onBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onLogin(View view) {
        if (authenticateUser(textFieldPassword.getText().toString(), textFieldEmail.getText().toString())) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Error during log in, please try again ! ", Toast.LENGTH_LONG);
        }

    }

    private boolean authenticateUser(String password, String email) {
        Cursor data = dbHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            listData.add(data.getString(5));
            listData.add(data.getString(7));
        }
        return PasswordUtils.verifyUserPassword(password, listData.get(0), listData.get(1));
    }

}
