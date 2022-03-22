package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.RoomTypes;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;

public class RoomType extends DatabaseTableRecord {
    private int roomTypeID;
    private double price;
    private String description;

    public RoomType(int roomTypeID, double price, String description){
        this.roomTypeID = roomTypeID;
        this.price = price;
        this.description = description;
    }
    @Override
    public int getRecordId() {
        return this.roomTypeID;
    }

    @Override
    public String getAbbreviatedInfo() {
        String info =   "Type: " + this.description +
                "Price: " + this.price;
        return info;
    }

    @Override
    public String getDetailedInfo() {
        String info =   "Type: " + this.description +
                "\nPrice: " + this.price;
        return info;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return null;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
