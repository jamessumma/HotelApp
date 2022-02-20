package com.example.myandroidsupportlibrary.DatabaseSupport;

import android.content.Context;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class LoginValidation
{
    private String username;
    private String password;
    private DatabaseController DBController;

    public LoginValidation(String username, String password,DatabaseController DBController)
    {
        this.password = password;
        this.username = username;
        this.DBController = DBController;
    }

    public abstract void onLoginSuccess(Context context);
    public abstract void onLoginFail(Context context);

    public abstract String getPasswordField();

    public abstract String getLoginTable();

    public abstract String getUsernameField();

    public boolean validate()
    {
        boolean success = false;
        String query = "select" + getPasswordField() +
                " from " + getLoginTable() + " where " +
                getUsernameField() + "="+username;

        try
        {
            ResultSet results = DBController.executeResultsQuery(query);
            if(results.next())
            {
                String correctPassword = results.getNString(1);
                if(correctPassword.equals(this.password))
                {
                    success = true;
                }

                DBController.closeConnection();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return success;
    }
}
