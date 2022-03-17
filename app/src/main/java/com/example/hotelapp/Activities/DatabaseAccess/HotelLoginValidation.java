package com.example.hotelapp.Activities.DatabaseAccess;


import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseLogin.LoginValidation;

import android.content.Context;

public class HotelLoginValidation extends LoginValidation {

    public HotelLoginValidation(DatabaseController databaseController,
                                String username,
                                String password)
    {
        super(databaseController,username,password);
    }

    @Override
    public String getUsernameField() {
        return "username";
    }

    @Override
    public String getPasswordField() {
        return "password";
    }

    @Override
    public String getLoginTable() {
        return "LoginInfo";
    }

    @Override
    public String getUserIdField() {
        return null;
    }

    @Override
    public void onLoginSuccess(Context context) {
        //add home screen here
    }

    @Override
    public void onLoginFail(Context context) {
        // add toast here
    }
}