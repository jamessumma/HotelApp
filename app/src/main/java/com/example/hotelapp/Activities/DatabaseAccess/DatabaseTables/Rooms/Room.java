package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Rooms;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;

public class Room extends DatabaseTableRecord {
    private int roomID;
    private String roomType;
    private int floor;
    private double price;

    public Room(int room, String roomType, int floor, double price){
        this.roomID = room;
        this.roomType = roomType;
        this.floor = floor;
        this.price = price;
    }
    @Override
    public int getRecordId() {
        return this.roomID;
    }

    @Override
    public String getAbbreviatedInfo() {
        return "Room type: " + this.roomType;
    }

    @Override
    public String getDetailedInfo() {
        String info =   "Room type: " + this.roomType +
                "\nRoom number: " + this.roomID +
                "\nFloor: " + this.floor +
                "\nPrice per night: " + this.price;
        return info;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return null;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public int getFloor() {
        return this.floor;
    }

    public double getPrice(){
        return this.price;
    }
}
