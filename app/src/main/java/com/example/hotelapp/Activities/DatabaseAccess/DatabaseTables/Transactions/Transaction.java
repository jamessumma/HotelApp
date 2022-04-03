package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;
import com.example.myandroidsupportlibrary.MetricConversionSupport.MetricConversion;

import java.sql.Date;
import java.sql.Time;

public class Transaction extends DatabaseTableRecord {

    private int transactionID;
    private int guestID;
    private String date;

    //private ArrayList<TransactionItem> items; Talk to kevin about it.

    private final static String DATE_FORMAT = "EEE dd, MMM yyyy";

    public Transaction(int transactionID, int guestID,Date date) {
        this.transactionID = transactionID;
        this.guestID = guestID;

        this.date = MetricConversion.convertDateToFormattedString(date,DATE_FORMAT);
    }

    @Override
    public int getRecordId() {
        return this.transactionID;
    }

    @Override
    public String getAbbreviatedInfo() {
        return "Transaction number " + this.transactionID + "\n" +
                "Guest ID " + this.guestID + "\n" +
                "Date Purchased " + this.date + "\n";
    }

    @Override
    public String getDetailedInfo() {
        return "Transaction number " + this.transactionID + "\n" +
                "Guest ID " + this.guestID + "\n" +
                "Date Purchased " + this.date + "\n";
    }

    @Override
    public String getFieldValue(String fieldName) {

        String result = "";

        switch (fieldName) {
            case "transactionID": {
                result = String.valueOf(this.transactionID);
                break;
            }
            case "guestID": {
                result = String.valueOf(this.guestID);
                break;
            }
            case "transactionDate": {
                result = this.date;
                break;
            }
            default: {
                break;
            }
        }
        return result;
    }
}
