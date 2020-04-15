package com.activities;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.management.UserManager;
import com.tickshare.R;



public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private Button btnNext;

    private EditText inputName, inputLastName, inputRegion,
            inputEmailAddress, inputPassword, inputConfirmPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        inputName = findViewById(R.id.inputTextCreateAccountName);
        inputLastName =findViewById(R.id.inputTextCreateAccountLastName);
        inputRegion = findViewById(R.id.inputTextCreateAccountRegion);
        inputEmailAddress =findViewById(R.id.inputTextCreateAccountEmailAddress);
        inputPassword = findViewById(R.id.inputTextCreateAccountPassword);
        inputConfirmPassword =findViewById(R.id.inputTextCreateAccountConfirmPassword);
        btnNext = findViewById(R.id.buttonCreateAccountNext);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        init();
    }

    private void init(){
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getEditableText().toString().trim();
                String lastName = inputLastName.getEditableText().toString().trim();
                String region = inputRegion.getEditableText().toString().trim();
                String emailAddress = inputEmailAddress.getEditableText().toString().trim();
                String password = inputPassword.getEditableText().toString().trim();
                String confirmPassword = inputConfirmPassword.getEditableText().toString().trim();
                if(password.equals(confirmPassword)){
                    //TODO: Rework whole method
                    UserManager userManager = new UserManager();
                    userManager.createUser(name,lastName,region,emailAddress,password);
                    System.out.println(userManager.getUserList().size());
                    System.out.println(userManager.getUserList().toString());
                }

            }
        });

    }

}
