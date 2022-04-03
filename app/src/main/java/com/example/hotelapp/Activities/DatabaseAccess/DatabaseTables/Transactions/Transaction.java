package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;
import com.example.myandroidsupportlibrary.MetricConversionSupport.MetricConversion;

import java.sql.Date;
import java.sql.Time;

public class Transaction extends DatabaseTableRecord {

    private int transactionItemID;
    private int transactionID;
    private String date;
    private int quantity;
    private int productID;
    private String productName;
    private double productPrice;
    private int guestID;

    //private ArrayList<TransactionItem> items; Talk to kevin about it.

    private final static String DATE_FORMAT = "EEE dd, MMM yyyy";

    public Transaction(int transactionItemID, int transactionID, int guestID, Date date, int quantity, int productID, String productName, double productPrice) {
        this.transactionItemID = transactionItemID;
        this.transactionID = transactionID;
        this.guestID = guestID;
        this.date = MetricConversion.convertDateToFormattedString(date,DATE_FORMAT);
        this.quantity = quantity;
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
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
            case "transactionItemID": {
                result = String.valueOf(this.transactionItemID);
                break;
            }
            case "transactionID": {
                result = String.valueOf(this.transactionID);
                break;
            }
            case "transactionDate": {
                result = this.date;
                break;
            }
            case "quantity": {
                result = String.valueOf(this.quantity);
                break;
            }
            case "productID": {
                result = String.valueOf(this.productID);
                break;
            }
            case "productName": {
                result = productName;
                break;
            }
            case "productPrice": {
                result = String.valueOf(this.productPrice);
                break;
            }
            case "guestID": {
                result = String.valueOf(this.guestID);
                break;
            }
        }
        return result;
    }
}
