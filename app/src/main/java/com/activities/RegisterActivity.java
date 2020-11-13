package com.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.authentication.Constants;
import com.persistence.DBHelper;
import com.tickshare.R;


public class RegisterActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private EditText inputName, inputLastName, inputRegion,
            inputEmailAddress, inputPassword, inputConfirmPassword;

    private AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initaliseEditTexts();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dbHelper = new DBHelper(this);

    }

    public void onNextClick(View view) {
        String name = inputName.getEditableText().toString().trim();
        String lastName = inputLastName.getEditableText().toString().trim();
        String region = inputRegion.getEditableText().toString().trim();
        String emailAddress = inputEmailAddress.getEditableText().toString().trim();
        String password = inputPassword.getEditableText().toString().trim();
        String confirmPassword = inputConfirmPassword.getEditableText().toString().trim();
        if (password.equals(confirmPassword)) {
            if (MainActivity.userManager.createUser(name, lastName, region, emailAddress, password) &&
                    dbHelper.addData(MainActivity.userManager.getUserFromEmail(emailAddress))) {
                showSuccessAlert();
                finish();
            } else {
                showErrorAlert();
                return;
            }
        }
    }

    private void initaliseEditTexts() {
        inputName = findViewById(R.id.inputTextCreateAccountName);
        inputLastName = findViewById(R.id.inputTextCreateAccountLastName);
        inputRegion = findViewById(R.id.inputTextCreateAccountRegion);
        inputEmailAddress = findViewById(R.id.inputTextCreateAccountEmailAddress);
        inputPassword = findViewById(R.id.inputTextCreateAccountPassword);
        inputConfirmPassword = findViewById(R.id.inputTextCreateAccountConfirmPassword);
    }

    private void showSuccessAlert() {
        Toast.makeText(getApplicationContext(), Constants.ACCOUNT_SUCCESS, Toast.LENGTH_LONG).show();
    }

    private void showErrorAlert() {
        dialog = new AlertDialog.Builder(this)
                .setTitle("Register")
                .setMessage("Could not create your account please check your entries: " + MainActivity.userManager.getErrorMap().values().toString())

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    public AlertDialog getAlertDialog() {
        return dialog;
    }


}
