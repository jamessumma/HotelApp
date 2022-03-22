package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Memberships.Membership;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;

import java.util.Date;

public class Guest extends DatabaseTableRecord {
    private int guestID;
    private String userName;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String password;
    private Membership membership;

    public Guest(int guestID, String userName, String firstName, String lastName, Date dateOfBirth, String password, Membership membership){
        this.guestID = guestID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.membership = membership;
    }

    @Override
    public int getRecordId() {
        return this.guestID;
    }

    @Override
    public String getAbbreviatedInfo() {
        return "Guest: " + this.firstName + " " + this.lastName;
    }

    @Override
    public String getDetailedInfo() {
        String info =   "Username: " + this.userName +
                "\nName: " + this.firstName + " " + this.lastName +
                "\nDOB: " + this.dateOfBirth +
                "\nMembership: " + this.membership.getMembershipType();
        return info;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return null;
    }

    public String getUserName(){return this.userName;}
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public Date getDateOfBirth(){return this.dateOfBirth;}
    public Membership getMembership() {
        return membership;
    }
}
