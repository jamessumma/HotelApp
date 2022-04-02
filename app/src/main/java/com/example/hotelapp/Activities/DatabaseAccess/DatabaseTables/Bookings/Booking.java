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
        String value;
        switch (fieldName){
            case "bookingID":
                value = String.valueOf(bookingID);
                break;
            case "name":
                value = this.guestFirstName + " " + this.guestLastName;
                break;
            case "roomNumber":
                value = String.valueOf(this.roomNumber);
                break;
            case "floor":
                value = String.valueOf(this.floorNumber);
                break;
            case "roomType":
                value = this.roomType;
                break;
            case "date":
                value = String.valueOf(this.startDate);
                break;
            case "duration":
                value = String.valueOf(this.duration);
                break;
            case "price":
                value = String.valueOf(this.price);
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}
