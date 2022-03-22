package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;

import java.util.ArrayList;
import java.util.Date;

public class Transaction extends DatabaseTableRecord {
    private int transactionID;
    private String guestUserName;
    private String guestName;
    private Date transactionDate;
    private ArrayList<TransactionItem> items;
    @Override
    public int getRecordId() {
        return this.transactionID;
    }

    @Override
    public String getAbbreviatedInfo() {
        String info = "";
        return null;
    }

    @Override
    public String getDetailedInfo() {
        return null;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return null;
    }
}
