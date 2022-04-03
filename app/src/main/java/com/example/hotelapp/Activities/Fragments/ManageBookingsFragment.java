package com.example.hotelapp.Activities.Fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Bookings.Booking;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Bookings.Bookings;
import com.example.hotelapp.Activities.DatabaseAccess.HotelLoginValidation;
import com.example.hotelapp.R;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseTasks.DatabaseTask;
import com.example.myandroidsupportlibrary.DrawableSupport.DrawableFactory;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicComponents.DataListView.DataListView;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicFragment;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Locale;


public class ManageBookingsFragment extends DynamicFragment {

    private Bookings bookings;
    private DataListView dataListView;

    public ManageBookingsFragment(){
        super();
        this.bookings = new Bookings(DatabaseTask.getDatabaseController());
        this.bookings.setAssociatedFragment(this);
        this.dataListView = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        super.onCreate(savedInstanceState);
        this.fragmentView.setBackgroundResource(R.drawable.hotel_booking_bg);

        if(this.dataListView == null){
            RecyclerView recyclerView = this.fragmentView.findViewById(R.id.recyclerView);

            this.dataListView = new DataListView(recyclerView, new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false), this.bookings);
            this.dataListView.setRetrievalValue(HotelLoginValidation.getUserId());
            this.dataListView.setItemLayoutId(R.layout.booking_layout);
            this.dataListView.setDataItemToViewRenderer((view, dataItem) -> transferBookingData(view, (Booking)dataItem));
            registerDynamicComponent(this.dataListView);
            this.dataListView.setRequiresUpdate(true);
            refresh(true);
        }
        return this.fragmentView;
    }

    private boolean transferBookingData(View view, Booking booking){
        boolean success = false;
        if(booking != null){
            TextView roomNumber = view.findViewById(R.id.roomNumber);
            TextView floorNumber = view.findViewById(R.id.floorNumber);
            TextView roomType = view.findViewById(R.id.roomType);
            TextView date = view.findViewById(R.id.date);
            TextView duration = view.findViewById(R.id.duration);

            roomNumber.setText("Room Number: " + booking.getFieldValue("roomNumber"));
            floorNumber.setText("Floor: " + booking.getFieldValue("floor"));
            roomType.setText("Room Type: " + booking.getFieldValue("roomType"));
            date.setText("Date: " + booking.getFieldValue("date"));
            duration.setText("Duration: " + booking.getFieldValue("duration"));

            success = true;
        }
        return success;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_manage_bookings;
    }
}