package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Bookings;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;

import java.util.Date;

public class Booking extends DatabaseTableRecord {
    private int bookingID;
    private String guestFirstName;
    private String guestLastName;
    private int roomNumber;
    private int floorNumber;
    private String roomType;
    private Date startDate;
    private int duration;
    private double price;

    public Booking(int bookingID, String guestFirstName, String guestLastName,
                   int roomNumber, int floorNumber, String roomType,
                   Date startDate, int duration, double price){
        this.bookingID = bookingID;
        this.guestFirstName = guestFirstName;
        this.guestLastName = guestLastName;
        this.roomNumber = roomNumber;
        this.floorNumber = floorNumber;
        this.roomType = roomType;
        this.startDate = startDate;
        this.duration = duration;
        this.price = price;
    }

    @Override
    public int getRecordId() {
        return this.bookingID;
    }

    @Override
    public String getAbbreviatedInfo() {
        return null;
    }

    @Override
    public String getDetailedInfo() {
        String info =   "Guest: " + this.guestFirstName + " " + this.guestLastName +
                "\nRoom: " + this.roomNumber +
                "\nFloor: " + this.floorNumber +
                "\nType: " + this.roomType +
                "\nBooking date: " + this.startDate +
                "\nDuration: " + this.duration +
                "\nPrice: $" + this.price;
        return info;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return null;
    }
}
