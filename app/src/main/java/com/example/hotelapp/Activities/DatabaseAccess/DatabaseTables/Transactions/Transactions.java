package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guests;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;

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
    public boolean retrieve(String retrievalValue){
        Guests guests = new Guests(this.databaseController);
        joinWithTable(guests, "guestID");
        boolean success = super.retrieve(retrievalValue);
        disjoin();
        return success;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        return false;
    }

}
