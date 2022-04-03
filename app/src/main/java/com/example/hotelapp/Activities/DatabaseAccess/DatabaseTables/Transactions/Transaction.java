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

    public Transaction(int transactionID, String guestUserName, String guestName, Date transactionDate, ArrayList<TransactionItem> items){
        this.transactionID = transactionID;
        this.guestUserName = guestUserName;
        this.guestName = guestName;
        this.transactionDate = transactionDate;
        this.items = items;
    }

    @Override
    public int getRecordId() {
        return this.transactionID;
    }

    @Override
    public String getAbbreviatedInfo() {
        return null;
    }

    @Override
    public String getDetailedInfo() {
        String info = "Transaction number: " + this.transactionID +
                "\nUsername: " + this.guestUserName +
                "\nGuest Name: " + this.guestName +
                "\nDate: " + this.transactionDate;
        for(TransactionItem i : this.items){
            info += "\n" + i.getDetailedInfo();
        }
        return info;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return null;
    }

    public ArrayList<TransactionItem> getItems(){return this.items; }
}
