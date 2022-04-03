package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import android.net.TrafficStats;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guests;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class Transactions extends DatabaseTable {
    public Transactions(DatabaseController databaseController) {
        super(databaseController);
        setRetrievalField(this.getPrimaryKey());
    }

    @Override
    public String getTableName() {
        return "transactions";
    }

    @Override
    public String getPrimaryKey() {
        return "transactionID";
    }

    @Override
    public boolean retrieve(String retrievalValue) {

        Guests guests = new Guests(this.databaseController);

        TransactionItems transactionItems = new TransactionItems(this.databaseController);

        this.joinWithTable(guests, "guestID");

        this.joinWithTable(transactionItems, "transactionID");

        boolean success = super.retrieve(retrievalValue);

        this.disjoin();

        return success;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {

        int transactionID = results.getInt("transactionID");
        int guestID = results.getInt("guestID");
        Date date = results.getDate("transactionDate");

        Transaction transaction = new Transaction(transactionID,guestID,date);

        this.getRecordArray().add(transaction);

        return true;
    }

}
