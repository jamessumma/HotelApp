package com.example.hotelapp.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guest;
import com.example.hotelapp.R;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseTasks.DatabaseTask;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.util.ArrayList;

public class CreateAccountActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText reenteredPassword;
    private Spinner membershipSpinner;
    private Button createAccountBtn;
    private DatabaseController databaseController;
    private DatabaseTable databaseTable;

    @Override
    protected void onCreate(Bundle resourceBundle) {

        super.onCreate(resourceBundle);
        this.setAttributes();
        this.fillSpinner();
        this.setCreateAccountBtnHandler();

        databaseController = DatabaseController.getDatabaseController();
        /*
        final String CONNECTIONSTRING = ""; // Fill these values
        final String USERNAME = "";
        final String PASSWORD = "";

        this.databaseController = DatabaseController.createDatabaseController(CONNECTIONSTRING,USERNAME,PASSWORD);
         */
    }

    private void setCreateAccountBtnHandler() {
        if(this.createAccountBtn != null){
            this.createAccountBtn.setOnClickListener(view -> {
                if(username != null && password != null && membershipSpinner != null && password.equals(reenteredPassword)) {

                    ArrayList<String> guestFields = new ArrayList<>();
                    guestFields.add("username");
                    guestFields.add("password");
                    guestFields.add("membershipType");
                    ArrayList<String> guestValues = new ArrayList<>();
                    guestValues.add(username.getText().toString());
                    guestValues.add(password.getText().toString());
                    guestValues.add(membershipSpinner.toString());
                    DatabaseTask insertAccountTask = new DatabaseTask.Insert(databaseController, databaseTable,
                                                                            databaseTable.getRetrievalField(),
                                                                            guestFields, guestValues);
                    insertAccountTask.execute();

                    Context context = view.getContext();
                    Intent intent = new Intent(context, MainActivity.class);

                    context.startActivity(intent);
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
    private void fillSpinner() {
        if(this.membershipSpinner != null) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.memberships,R.layout.create_account_activity);
            adapter.setDropDownViewResource(R.id.membership_create_account_spinner);
            this.membershipSpinner.setAdapter(adapter);
        }
    }





}
