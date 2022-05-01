package com.example.hotelapp.Activities.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.hotelapp.Activities.CreateAccountActivity;
import com.example.hotelapp.Activities.CreateBookingActivity;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Bookings.Booking;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Bookings.Bookings;
import com.example.hotelapp.Activities.DatabaseAccess.HotelLoginValidation;
import com.example.hotelapp.R;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseTasks.DatabaseTask;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicFragment.ListViewFragment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;


public class ManageBookingsFragment extends ListViewFragment {

    private Bookings bookings;
    private Button addBookingButton;

    public ManageBookingsFragment(Activity activity){
        super();
        this.bookings = new Bookings(DatabaseTask.getDatabaseController());
        this.bookings.setAssociatedFragment(this);
        setListViewDialog(new BookingDialog(activity));
    }

    @Override
    public int getRecyclerViewId() {
        return R.id.recyclerView;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.booking_layout;
    }

    @Override
    public DatabaseTable getDatabaseTable() {
        return this.bookings;
    }

    @Override
    public String getRetrievalValue() {
        return HotelLoginValidation.getUserId();
    }

    @Override
    protected boolean transferData(View view, DatabaseTableRecord databaseTableRecord) {
        return transferBookingData(view, (Booking)databaseTableRecord);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        super.onCreate(savedInstanceState);
        this.fragmentView.setBackgroundResource(R.drawable.hotel_booking_bg);
        System.out.println("=======================================");
        addBookingButton = this.fragmentView.findViewById(R.id.addBookingButton);
        addBookingButton.setOnClickListener(view -> {
            Context context = view.getContext();
            Intent intent = new Intent(context, CreateBookingActivity.class);
            context.startActivity(intent);
        });
        return this.fragmentView;
    }

    private boolean transferBookingData(View view, Booking booking){
        boolean success = false;
        if(booking != null){
//            TextView roomNumber = view.findViewById(R.id.roomNumber);
//            TextView floorNumber = view.findViewById(R.id.floorNumber);
//            TextView roomType = view.findViewById(R.id.roomType);
            TextView date = view.findViewById(R.id.date);
            TextView duration = view.findViewById(R.id.duration);
            CheckBox checkbox = view.findViewById(R.id.checkbox);

//            roomNumber.setText("Room Number: " + booking.getFieldValue("roomNumber"));
//            floorNumber.setText("Floor: " + booking.getFieldValue("floor"));
//            roomType.setText("Room Type: " + booking.getFieldValue("roomType"));
            date.setText("Date: " + booking.getFieldValue("date"));
            duration.setText("Duration: " + booking.getFieldValue("duration") + " days");
            if (getSelectedItemsCount() == 0)
            {
                checkbox.setOnCheckedChangeListener(null);
                checkbox.setChecked(false);
            }

            //set click handler for checkbox
            checkbox.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    selectItem(booking);
                }
            });
            success = true;
        }
        return success;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_manage_bookings;
    }
}