package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;


import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guests;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Products.Products;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseTasks.DatabaseTask;
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
        return "guestID";
    }

    @Override
    public boolean retrieve(String retrievalValue) {
        boolean success = false;

        TransactionItems transactionItems = new TransactionItems(DatabaseTask.getDatabaseController());

        Products products = new Products(DatabaseTask.getDatabaseController());

        transactionItems.joinWithTable(products,"productID");

        this.joinWithTable(transactionItems,"transactionID");

        success = super.retrieve(retrievalValue);

        transactionItems.disjoin();
        disjoin();

        return success;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {

         int transactionItemID = results.getInt("transactionItemID");
         int transactionID = results.getInt("transactionID");
         Date date = results.getDate("transactionDate");
         int quantity = results.getInt("quantity");
         int productID = results.getInt("productID");
         String productName = results.getString("productName");
         double productPrice = results.getDouble("productPrice");
         int guestID = results.getInt("guestID");

         Transaction transaction = new Transaction(transactionItemID,transactionID,guestID,date,quantity,productID,productName,productPrice);

         this.getRecordArray().add(transaction);

         return true;
    }

}
