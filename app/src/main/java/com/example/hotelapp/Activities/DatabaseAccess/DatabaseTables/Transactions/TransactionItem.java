package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;
import com.example.myandroidsupportlibrary.MetricConversionSupport.MetricConversion;

import java.sql.Date;
import java.sql.Time;

public class TransactionItem extends DatabaseTableRecord {

    /** Kevin need input from, he smart, he know best
    private String itemName;
    private double itemPrice;
    private int quantity;
     */
    private int transactionItemID;
    private int transactionID;
    private int productID;
    private int quantity;

    private final static String DATE_FORMAT = "EEE dd, MMM yyyy";

    public TransactionItem(int transactionItemID, int transactionID, int productID, int quantity) {

        /** Kevin need input from, he smart, he know best
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
         */

        this.transactionItemID = transactionItemID;
        this.transactionID = transactionID;
        this.quantity = quantity;
        this.productID = productID;
    }

    @Override
    public int getRecordId() {
        return this.transactionItemID;
    }

    @Override
    public String getAbbreviatedInfo() {
        return "Transaction " + this.transactionID + "\n" +
                "quantity " + this.quantity + "\n" +
                "product ID " + this.productID + "\n";
    }

    @Override
    public String getDetailedInfo() {
        return "Transaction Item ID" + this.transactionItemID + "\n" +
                "Transaction " + this.transactionID + "\n" +
                "quantity " + this.quantity + "\n" +
                "product ID " + this.productID + "\n";
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
            case "quantity": {
                result = String.valueOf(this.quantity);
                break;
            }
            case "productID": {
                result = String.valueOf(this.productID);
                break;
            }
        }

        return result;
    }
}
