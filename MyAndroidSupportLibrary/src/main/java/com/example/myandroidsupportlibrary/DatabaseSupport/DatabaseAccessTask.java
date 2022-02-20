package com.example.myandroidsupportlibrary.DatabaseSupport;

import android.content.Context;
import android.os.AsyncTask;

public class DatabaseAccessTask extends AsyncTask<Integer,Integer,Long>
{
    private DatabaseController databaseController;
    private TaskDescription taskDescription;
    private Context context;

    public DatabaseAccessTask(DatabaseController databaseController,
                              TaskDescription taskDescription, Context context)
    {
        this.databaseController = databaseController;
        this.taskDescription = taskDescription;
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        super.onProgressUpdate(values);
    }

    @Override
    protected Long doInBackground(Integer... integers)
    {
        int code = taskDescription.getCode();

        switch(code)
        {
            case TaskDescription.CONNECT_DATABASE_TASK:
                boolean success = databaseController.openConnection();
                if(success)
                    System.out.println("Opened BD connection successfully!!");
                break;
            case TaskDescription.LOGIN_DATABASE_TASK:
                LoginValidation loginValidation = (LoginValidation) taskDescription.getParam(0);

                boolean loginSuccess = loginValidation.validate();
                if(loginSuccess)
                {
                }
                break;

        }

        return null;
    }

    @Override
    protected void onPostExecute(Long aLong)
    {
        super.onPostExecute(aLong);
    }
}
