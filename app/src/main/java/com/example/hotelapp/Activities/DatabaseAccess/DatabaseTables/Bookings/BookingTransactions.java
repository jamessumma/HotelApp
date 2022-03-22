package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Bookings;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;

public class BookingTransactions extends DatabaseTable {
    public BookingTransactions(DatabaseController databaseController) {
        super(databaseController);
        setRetrievalField(this.getPrimaryKey());
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String getPrimaryKey() {
        return null;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        return false;
    }
}
