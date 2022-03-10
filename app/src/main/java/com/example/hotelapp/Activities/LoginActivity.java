package com.example.hotelapp.Activities;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelapp.R;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseLogin.LoginValidation;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseTasks.DatabaseTask;
public class LoginActivity extends AppCompatActivity {
    public EditText usernameEditText;
    public EditText passwordEditText;
    public Button signInButton;
    public DatabaseController databaseController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        /* Set the connection string to the database and the username/password, its not a good practice to hard code these in
         * the application its better to connect to a web service to do the actual login in our behalf, otherwise a malicious
         * user could reverse engineer the app and get much more unrestricted access to all the database server. For the time
         * being we are doing it like this to save some time and to focus on android development rather than investing time
         * on web programming. Those of you that want to have this in your app, I will be coordinating with you to implement
         * this web service functionality in a way that works for your app and all others. */
        // TODO: EDIT THIS
        String connectionString = "jdbc:jtds:sqlserver://10.0.2.2:1433;databaseName:MedicalCenter;instance=STEVESERVER";
        String username = "sa";
        String password = "mouserat";

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameTextEdit);
        passwordEditText = findViewById(R.id.passwordTextEdit);
        signInButton = findViewById(R.id.signInButton);

        //Create a new database controller object in order to connect to the database
        databaseController = new DatabaseController(connectionString, username, password);
        //Make sure java class implementing jdbc driver has been loaded before attempting to connect to database
        try
        {
            databaseController.setDriver("net.sourceforge.jtds.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Unable to load jtds driver, won't be able to connect to" +
                    "mssql server");
        }

        DatabaseTask connectToDBTask = new DatabaseTask.Connect(databaseController, null);
        connectToDBTask.execute();

        //Add an event handler to the button so that we login to the database when the button is clicked
        signInButton.setOnClickListener(new View.OnClickListener()
                                        {
                                            @Override
                                            public void onClick(View view)
                                            {
                                                String username = usernameEditText.getText().toString();
                                                String password = passwordEditText.getText().toString();

                                                com.example.hotelApp.Activities.DatabaseAccess.HotelLoginValidation medicalCenterLoginValidation =
                                                        new com.example.hotelApp.Activities.DatabaseAccess.HotelLoginValidation(databaseController, username, password);

                                                LoginValidation.performLoginValidation(medicalCenterLoginValidation, LoginActivity.this);

                                            }
                                        }
        );



    }




}
