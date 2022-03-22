package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Memberships;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;

public class Membership extends DatabaseTableRecord {
    private int membershipId;
    private String membershipType;
    private double discountPercent;

    public Membership(int membershipID, String membershipType, double discountPercent){
        this.membershipId = membershipID;
        this.membershipType = membershipType;
        this.discountPercent = discountPercent;
    }
    @Override
    public int getRecordId() {
        return this.membershipId;
    }

    @Override
    public String getAbbreviatedInfo() {
        return "Membership type: " + this.membershipType;
    }

    @Override
    public String getDetailedInfo() {
        String info =   "Membership type: " + this.membershipType +
                "\nDiscount Percent: " + this.discountPercent;
        return info;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return null;
    }

    public int getMembershipId() {
        return this.membershipId;
    }

    public String getMembershipType() {
        return this.membershipType;
    }

    public double getDiscountPercent() {
        return this.discountPercent;
    }
}
