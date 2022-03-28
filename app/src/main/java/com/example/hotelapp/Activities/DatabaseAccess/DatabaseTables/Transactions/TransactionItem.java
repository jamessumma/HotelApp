package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;

public class TransactionItem extends DatabaseTableRecord {
    private String itemName;
    private double itemPrice;
    private int quantity;

    public TransactionItem(String itemName, double itemPrice, int quantity){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    @Override
    public int getRecordId() {
        return 0;
    }

    @Override
    public String getAbbreviatedInfo() {
        return null;
    }

    @Override
    public String getDetailedInfo() {
        return this.itemName + "\t" + this.itemPrice + "\tx" + this.quantity;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return null;
    }
}
