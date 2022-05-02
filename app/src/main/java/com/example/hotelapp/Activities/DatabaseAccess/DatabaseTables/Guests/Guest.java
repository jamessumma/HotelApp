package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Memberships.Membership;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;

import java.util.Date;

public class Guest extends DatabaseTableRecord {
    private int guestID;
    private String userName;
    private String firstName;
    private String emailAddress;
    private String lastName;
    private Date dateOfBirth;
    private String password;
    private Membership membership;

    public Guest(int guestID, String userName, String firstName, String lastName, Date dateOfBirth, String password, Membership membership, String emailAddress){
        this.guestID = guestID;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.membership = membership;
        this.emailAddress = emailAddress;
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
    public String getFieldValue(String fieldName)
    {
        String fieldValue = "";
        switch (fieldName)
        {
            case "guestid":
                fieldValue = String.valueOf(guestID);
                break;
            case "firstname":
                fieldValue = firstName;
                break;
            case "fullname":
                fieldValue = firstName;
                fieldValue += " ";
                fieldValue += lastName;
                break;
            case "dob":
                fieldValue = String.valueOf(dateOfBirth);
                break;
            case "username":
                fieldValue = String.valueOf(userName);
                break;
            case "password":
                fieldValue = String.valueOf(password);
                break;
            case "membership":
                fieldValue = String.valueOf(membership);
                break;
            case "email":
                fieldValue = emailAddress;
                break;

            case "test":
                return "test";
        }
        return fieldValue;
    }
    public String getUserName(){return this.userName;}
    public String getFirstName(){return this.firstName;}
    public String getLastName(){return this.lastName;}
    public Date getDateOfBirth(){return this.dateOfBirth;}
    public Membership getMembership() {
        return membership;
    }
}
