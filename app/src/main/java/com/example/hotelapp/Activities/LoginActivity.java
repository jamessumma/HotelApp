package com.example.hotelApp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotel_app.R;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseController;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signinBtn;
    private Button createAccountBtn;
    private DatabaseController databaseController;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_login);

        this.usernameEditText = this.findViewById(R.id.usernameTextEdit);
        this.passwordEditText = this.findViewById(R.id.passwordTextEdit);
        this.signinBtn = this.findViewById(R.id.signInButton);
        this.createAccountBtn = this.findViewById(R.id.createAccountBtn);

        this.setEventHandlers();
    }

    private void setEventHandlers(){
        if(this.createAccountBtn != null && this.signinBtn != null) {
            this.setSignHandler();
            this.setCreateAccountHandler();
        }
    }

    private void setSignHandler() {
        if(this.usernameEditText != null && this.passwordEditText != null) {

            this.signinBtn.setOnClickListener(
                    new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    private void setCreateAccountHandler() {
        this.createAccountBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LoginActivity.this, createAccountActivity.class);
                        LoginActivity.this.startActivity(intent);
                    }
                }
        );
    }


}
