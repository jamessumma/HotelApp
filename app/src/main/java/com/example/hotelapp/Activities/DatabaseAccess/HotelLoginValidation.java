package com.example.hotelapp.Activities.DatabaseAccess;

import com.example.hotelapp.Activities.LoginActivity;
import com.example.hotelapp.Activities.MainActivity;
import com.example.hotelapp.R;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseLogin.LoginValidation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

public class HotelLoginValidation extends LoginValidation {

    public HotelLoginValidation(DatabaseController databaseController,
                                String username,
                                String password)
    {
        super(databaseController,username,password);
    }

    @Override
    public String getUsernameField() {
        return "userName";
    }

    @Override
    public String getPasswordField() {
        return "password";
    }

    @Override
    public String getLoginTable() {
        return "guests";
    }

    @Override
    public String getUserIdField() {
        return "guestID";
    }

    @Override
    public void onLoginSuccess(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onLoginFail(Context context) {
        /*
        context.getApplicationContext().runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
        //Looper.prepare();
        Toast toast = new Toast(context);
        toast.setText("Login Failed");
        //toast.setDuration(Toast.LENGTH_LONG);
        //toast.show();

         */
    }
}
