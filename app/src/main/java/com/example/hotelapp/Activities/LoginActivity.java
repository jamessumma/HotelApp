package com.example.hotelapp.Activities;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelapp.Activities.DatabaseAccess.HotelLoginValidation;
import com.example.hotelapp.R;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseLogin.LoginValidation;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseTasks.DatabaseTask;
public class LoginActivity extends AppCompatActivity {

    public EditText usernameEditText;
    public EditText passwordEditText;
    public Button signInButton;
    private Button createAccountBtn;
    private Button helpBtn;
    public DatabaseController databaseController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        String connectionString = "jdbc:jtds:sqlserver://10.0.2.2:1433;databaseName=hotel;instance=SQLEXPRESS";
        String username = "sa";
        String password = "password";

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameTextEdit);
        passwordEditText = findViewById(R.id.passwordTextEdit);
        signInButton = findViewById(R.id.signInButton);
        createAccountBtn = findViewById(R.id.create_account_btn);
        helpBtn = findViewById(R.id.helpBtn);

        //Create a new database controller object in order to connect to the database
        databaseController = DatabaseController.createDatabaseController(connectionString, username, password);
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

        DatabaseTask connectToDBTask = new DatabaseTask.Connect(databaseController);
        connectToDBTask.execute();

        //Add an event handler to the button so that we login to the database when the button is clicked
        signInButton.setOnClickListener(view ->
                {
                    String username1 = usernameEditText.getText().toString();
                    String password1 = passwordEditText.getText().toString();


                    HotelLoginValidation hotelLoginValidation =
                            new HotelLoginValidation(databaseController, username1, password1);

                    LoginValidation.performLoginValidation(hotelLoginValidation, LoginActivity.this);

                }
            );

        //Add an event handler so that we enter the CreateAccountActivity when the create account button is clicked
        createAccountBtn.setOnClickListener(view->
                {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, CreateAccountActivity.class);
                    context.startActivity(intent);
                }
            );
    }




}
