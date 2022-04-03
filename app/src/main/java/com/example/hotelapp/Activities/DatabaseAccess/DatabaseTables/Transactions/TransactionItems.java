package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guests;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Products.Products;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.Date;
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
    public boolean retrieve(String retrievalValue) {

        boolean success = false;

        success = super.retrieve(retrievalValue);

        return success;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {

        int transactionItemID = results.getInt("transactionItemID");
        int transactionID = results.getInt("transactionID");
        int quantity = results.getInt("quantity");
        int productID = results.getInt("productID");

        TransactionItem transactionItem = new TransactionItem(transactionItemID,transactionID,productID,quantity);

        this.getRecordArray().add(transactionItem);

        return true;
    }
}
