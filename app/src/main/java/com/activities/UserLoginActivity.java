package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.authentication.PasswordUtils;
import com.tickshare.R;

public class UserLoginActivity extends AppCompatActivity {
    private EditText textFieldEmail, textFieldPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textFieldEmail = findViewById(R.id.inputTextEmailAddressLogin);
        textFieldPassword = findViewById(R.id.inputTextPasswordLogin);
    }

    public void onBack(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void onLogin(View view){
        //TODO: Change this later
        System.out.println(authenticateUser(textFieldPassword.getText().toString()));
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private boolean authenticateUser(String password){
        return PasswordUtils.verifyUserPassword(password,MainActivity.userManager.getUserFromEmail(textFieldEmail.toString()).getPassword(),MainActivity.userManager.getUserFromEmail(textFieldEmail.toString()).getSalt() );
    }
}
