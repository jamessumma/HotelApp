package com.example.hotelapp.Activities.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guest;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guests;
import com.example.hotelapp.Activities.DatabaseAccess.HotelLoginValidation;
import com.example.hotelapp.R;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseTasks.DatabaseTask;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicComponents.DataItemToView;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicComponents.DataItemView;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicComponents.DynamicComponent;
import com.example.myandroidsupportlibrary.FragmentSupport.DynamicFragment.DynamicFragment;

import java.util.ArrayList;


public class ManageAccountFragment extends DynamicFragment {

    private Guests guests;
    private DataItemView guestInfoView;
    private Button editContactInfo;
    private String pk;
    //retrieve items method,
    public ManageAccountFragment()
    {
        super();
        guests = new Guests(DatabaseController.getDatabaseController());
        guests.setAssociatedFragment(this);
        guests.setRetrievalField("guestID");
        //DatabaseTask.Update update = new DatabaseTask.Update();
    }


    public DatabaseTable getDatabaseTable(){
        return guests;
    }

    protected boolean transferData(View view,
                                   DatabaseTableRecord databaseTableRecord)
    {
        return transferGuestData(view,
                (Guest)databaseTableRecord);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initializeGuestInfoView();
        View v = super.onCreateView(inflater, container, savedInstanceState);
        v.findViewById(R.id.editInfoBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("button clicked");
                ArrayList<String> updateFields = new ArrayList<>();
                updateFields.add("emailAddress");
                ArrayList<String> updateValues = new ArrayList<>();
                updateValues.add("newestEmail@example.com");
                ArrayList<String> keyFields = new ArrayList<>();
                keyFields.add("guestID");
                ArrayList<String> keyValues = new ArrayList<>();
                keyValues.add(guests.getPrimaryKey());

                updateItems(guests, "", updateFields, updateValues, keyFields, keyValues);
                //DatabaseTask.Update update = new DatabaseTask.Update(DatabaseController.getDatabaseController(), guests, guests.getRetrievalField(), updateFields, keyFields, keyValues );
            }
        });
        return v;
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.fragment_manage_account;
    }

    private void initializeGuestInfoView()
    {
        if (guestInfoView == null){
            guestInfoView = new DataItemView(guests);
            guestInfoView.setXmlViewId(R.id.mainAccountLayout);
            guestInfoView.setRetrievalValue(HotelLoginValidation.getUserId());
            DataItemView dataItemView = new DataItemView(guests);

            guestInfoView.setDataItemToViewRenderer( new DataItemToView()
            {
                @Override
                public boolean transferItemData(View view, DatabaseTableRecord dataItem)
                {
                    return transferGuestData(view, (Guest) dataItem);
                }
            });
            registerDynamicComponent(guestInfoView);
            guestInfoView.setRequiresUpdate(true);
            refresh(true);
        }

    }
    private boolean transferGuestData(View view, Guest guest)
    {
        boolean success = false;

        if (guest != null )
        {
            System.out.println("Loading account info");
            TextView nameTextView = view.findViewById(R.id.nameTextView);
            TextView usernameTextView = view.findViewById(R.id.usernameTextView);
            TextView emailTextView = view.findViewById(R.id.emailTextView);
            TextView dobTextView = view.findViewById(R.id.dobTextView);
            this.editContactInfo = this.getView().findViewById(R.id.editInfoBtn);

            nameTextView.setText(guest.getFieldValue("fullname"));
            emailTextView.setText(guest.getFieldValue("email"));
            dobTextView.setText(guest.getFieldValue("dob"));
            usernameTextView.setText(guest.getFieldValue("username"));

            success = true;
        }
        return success;
    }
    @Override
    public void updateComponents(DatabaseTable updatedTable) {
        super.updateComponents(updatedTable);
        if (guestInfoView != null){
            guestInfoView.setCurItem(0);
        }

    }

}