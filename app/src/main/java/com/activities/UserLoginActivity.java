package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.authentication.PasswordUtils;
import com.model.IUser;
import com.tickshare.R;

public class UserLoginActivity extends AppCompatActivity {
    private EditText textFieldEmail, textFieldPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textFieldEmail = findViewById(R.id.inputTextEmailAddressLogin);
        textFieldPassword = findViewById(R.id.inputTextPasswordLogin);
        MainActivity.userManager.createUser("Paul","Wüsthoff","Berlin","paulwpaul@web.de","1234567");
    }

    public void onBack(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void onLogin(View view){
        if(authenticateUser(textFieldPassword.getText().toString(),textFieldEmail.getText().toString())){
           finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Error during log in, please try again ! ", Toast.LENGTH_LONG);
        }

    }
    private boolean authenticateUser(String password,String email){
        IUser user = MainActivity.userManager.getUserFromEmail(email);
        return PasswordUtils.verifyUserPassword(password, user.getPassword(), user.getSalt());
    }

}
