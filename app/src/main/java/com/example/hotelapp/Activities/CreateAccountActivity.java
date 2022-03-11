package com.example.hotelapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hotelapp.R;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText reenteredPassword;
    private Spinner membershipSpinner;
    private Button createAccountBtn;
    private DatabaseController databaseController;

    @Override
    protected void onCreate(Bundle resourceBundle) {
        super.onCreate(resourceBundle);
        this.setAttributes();
        this.fillSpinner();
        this.setCreateAccountBtnHandler();

        final String CONNECTIONSTRING = ""; // Fill these values
        final String USERNAME = "";
        final String PASSWORD = "";

        this.databaseController = new DatabaseController(CONNECTIONSTRING,USERNAME,PASSWORD);
    }

    private void setCreateAccountBtnHandler() {
        if(this.createAccountBtn != null){
            this.createAccountBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(username != null && password != null && reenteredPassword != null && membershipSpinner != null) {
                        /*
                         Create access task to create account
                            if account created
                                Return to login screen
                            else if not
                                 display a toast explaining what went wrong
                         */

                        Intent intent = new Intent(CreateAccountActivity.this, com.example.hotelapp.Activities.LoginActivity.class);

                        CreateAccountActivity.this.startActivity(intent);
                    }
                }
            });
        }
    }
    private void setAttributes() {
        this.username = this.findViewById(R.id.username_create_account_textEdit);
        this.password = this.findViewById(R.id.password_create_account_textEdit);
        this.reenteredPassword = this.findViewById(R.id.reenter_password_create_account_textEdit);
        this.membershipSpinner = this.findViewById(R.id.membership_create_account_spinner);
        this.createAccountBtn = this.findViewById(R.id.create_account_btn);
    }

    @SuppressLint("ResourceType")
    private void fillSpinner(){
        if(this.membershipSpinner != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.memberships,R.layout.create_account_activity);
            adapter.setDropDownViewResource(R.id.membership_create_account_spinner);
            this.membershipSpinner.setAdapter(adapter);
        }
    }





}
