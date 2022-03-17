package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;

public class TransactionItems extends DatabaseTable {
    public TransactionItems(DatabaseController databaseController) {
        super(databaseController);
        setRetrievalField(this.getPrimaryKey());
    }

    @Override
    public String getTableName() {
        return "transaction_items";
    }

    @Override
    public String getPrimaryKey() {
        return "transactionItemID";
    }

    @Override
    public boolean retrieve(String retrievalValue){
        return false;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        return false;
    }
}
