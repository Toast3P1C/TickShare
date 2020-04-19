package com.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.management.UserManager;
import com.tickshare.R;

public class MainActivity extends AppCompatActivity {
    public static UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();
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
}
