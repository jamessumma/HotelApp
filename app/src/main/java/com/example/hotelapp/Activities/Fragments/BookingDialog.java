package com.example.hotelapp.Activities.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Bookings.Booking;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;
import com.example.myandroidsupportlibrary.DialogSupport.ItemDialog;
import com.example.hotelapp.R;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicComponents.DataItemToView;
import com.example.myandroidsupportlibrary.LayoutSupport.LayoutFormatter;
import com.example.myandroidsupportlibrary.MetricConversionSupport.MetricConversion;

public class BookingDialog extends ItemDialog {
    private static final int DIALOG_WIDTH = 400;
    private static final int DIALOG_HEIGHT = 400;
    //private static int  SCROLLVIEW_HEIGHT = 330;
    private static int  BUTTON_WIDTH = 100;
    private static  int BUTTON_MARGIN = 125;
    private static String BUTTON_TEXT_COLOR = "#FFFFFF";
    private static String BUTTON_BACKGROUND_COLOR = "#0000FF";

    public BookingDialog(Activity activity)
    {
        super(activity, MetricConversion.dpToPixels(DIALOG_WIDTH),
                MetricConversion.dpToPixels(DIALOG_HEIGHT));
        setDataItemToViewRenderer(new DataItemToView()
        {
            @Override
            public boolean transferItemData(View view, DatabaseTableRecord dataItem)
            {
                return transferBookingData(view, (Booking) dataItem);
            }
        });

        //Add a button that allows us to close the dialog window
        Button closebutton = LayoutFormatter.createButton(getContext(), MetricConversion.dpToPixels(BUTTON_WIDTH),
                ViewGroup.LayoutParams.WRAP_CONTENT, Color.parseColor(BUTTON_BACKGROUND_COLOR), "Return",
                Typeface.BOLD, Color.parseColor(BUTTON_TEXT_COLOR),
                new int[]{MetricConversion.dpToPixels(BUTTON_MARGIN), 0, 0, 0});
        addButton(closebutton, DefaultButtonHandler.CLOSE);
    }

    @Override
    public int getXMLLayoutId()
    {
        return R.layout.booking_detailed_layout;
    }

    /* Define how each appointment database table record displays in the listview, this code is highly dependent on the
     * xml layout file for the listview item */
    private boolean transferBookingData(View view, Booking booking)
    {
        boolean success = false;
        if (booking != null)
        {
            TextView apptIdTextView = view.findViewById(R.id.bookingDetails);
            apptIdTextView.setText(booking.getDetailedInfo());

            success = true;
        }

        return success;
    }
}