package com.example.hotelapp.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Bookings.Bookings;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guest;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guests;
import com.example.hotelapp.Activities.DatabaseAccess.HotelLoginValidation;
import com.example.hotelapp.Activities.Fragments.ManageBookingsFragment;
import com.example.hotelapp.R;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseTasks.DatabaseTask;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CreateBookingActivity extends AppCompatActivity {

    private NumberPicker numberPicker;
    private DatePicker datePicker;
    private Button addBookingButton;
    private Button cancelBookingButton;
    private Spinner roomTypeSpinner;
    private DatabaseController databaseController;
    private Bookings bookings;

    @Override
    protected void onCreate(Bundle resourceBundle) {
        super.onCreate(resourceBundle);
        this.databaseController = DatabaseController.getDatabaseController();
        this.setContentView(R.layout.activity_create_booking);
        this.setAttributes();
        this.bookings = new Bookings(DatabaseController.getDatabaseController());
        this.setAddBookingButtonHandler();
        this.setCancelBookingButtonHandler();
    }

    private void setAddBookingButtonHandler() {
        if(this.addBookingButton != null){
            this.addBookingButton.setOnClickListener(view -> {

                ArrayList<String> fieldNames = new ArrayList<>(Arrays.asList("guestID", "roomID", "startDate", "duration"));
                ArrayList<String> values = new ArrayList<>();
                values.add(HotelLoginValidation.getUserId());
                values.add("1");
                values.add("'" + datePicker.getYear() + "-" + (datePicker.getMonth()+1) + "-" + datePicker.getDayOfMonth() + "'");
                values.add(String.valueOf(numberPicker.getValue()));

                DatabaseTask insertBookingTask = new DatabaseTask.Insert(databaseController, bookings,
                        bookings.getRetrievalField(),
                        fieldNames, values);
                insertBookingTask.execute();

                Context context = view.getContext();
                Intent intent = new Intent(context, MainActivity.class);

                context.startActivity(intent);
            });
        }
    }

    private  void setCancelBookingButtonHandler(){
        if(this.cancelBookingButton != null){
            this.cancelBookingButton.setOnClickListener(view ->{
                Context context = view.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            });
        }
    }

    private void setAttributes() {
        this.datePicker = this.findViewById(R.id.datePicker);
        this.numberPicker = this.findViewById(R.id.numberPicker);
        this.addBookingButton = this.findViewById(R.id.addBookingButton);
        this.roomTypeSpinner = this.findViewById(R.id.roomTypeSpinner);
        this.numberPicker.setMinValue(1);
        this.numberPicker.setMaxValue(21);
        this.datePicker.setMinDate(System.currentTimeMillis() - 1000); // prevent user from picking a date in the past
        this.cancelBookingButton = this.findViewById(R.id.cancelBookingButton);
        this.cancelBookingButton.setMinimumWidth(this.addBookingButton.getMeasuredWidth());
    }
}
