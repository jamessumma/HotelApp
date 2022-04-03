package com.example.hotelapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.hotelapp.Activities.Fragments.ExtrasFragment;
import com.example.hotelapp.Activities.Fragments.ManageAccountFragment;
import com.example.hotelapp.Activities.Fragments.ManageBookingsFragment;
import com.example.hotelapp.Activities.Fragments.PurchasesFragment;
import com.example.hotelapp.R;
import com.example.myandroidsupportlibrary.LayoutSupport.FragmentPager;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentPager fragmentPager;
    private Button logoutBtn;
    private Button checkInCheckOutBtn;
    private ManageAccountFragment manageAccountFragment;
    private PurchasesFragment purchasesFragment;
    private ManageBookingsFragment manageBookingsFragment;
    private ExtrasFragment extrasFragment;

    @Override
    protected void onCreate(Bundle resourceBundle) {
        super.onCreate(resourceBundle);
        this.setAttributes();
        this.setHandlers();
        this.setContentView(R.layout.main_activity);
        this.getSupportActionBar().hide();

        ViewPager pager = this.findViewById(R.id.view_pager);
        TabLayout tableLayout = this.findViewById(R.id.tab_layout);

        List<Fragment> fragments = Arrays.asList(manageAccountFragment,purchasesFragment,extrasFragment,manageBookingsFragment);

        List<String> titles = Arrays.asList("Manage Account","Purchases","Extras","Manage Bookings");

        this.fragmentPager = new FragmentPager(pager,this.getSupportFragmentManager(),fragments,titles);

        this.fragmentPager.bind(tableLayout);

    }

    private void setCheckInCheckOutHandler() {
        if(this.checkInCheckOutBtn != null) {
            this.checkInCheckOutBtn.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //TODO add some stuff
                        }
                    }
            );
        }
    }

    private void setLogoutHandler() {
        if(this.logoutBtn != null) {
            this.logoutBtn.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //TODO add some stuff
                        }
                    }
            );
        }
    }
    private void setHandlers(){
        this.setLogoutHandler();
        this.setCheckInCheckOutHandler();
    }

    private void setAttributes() {
        this.logoutBtn = this.findViewById(R.id.logout_btn);
        this.checkInCheckOutBtn = this.findViewById(R.id.check_in_checkout_btn);
        this.manageAccountFragment = new ManageAccountFragment();
        this.purchasesFragment = new PurchasesFragment();
        this.manageBookingsFragment = new ManageBookingsFragment();
        this.extrasFragment = new ExtrasFragment();
    }

    @Override
    public void onBackPressed() {
        if (fragmentPager.getCurrentFragmentPosition() == 0) {
            super.onBackPressed();
        }
        else {
            this.fragmentPager.setCurrentFragmentPosition(fragmentPager.getCurrentFragmentPosition() - 1);
        }
    }
}
