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

        Products products = new Products(this.databaseController);

        Guests guests = new Guests(this.databaseController);

        Transactions transactions = new Transactions(this.databaseController);

        transactions.joinWithTable(guests,"guestID");

        this.joinWithTable(products,"productID");

        this.joinWithTable(transactions,"transactionID");

        success = super.retrieve(retrievalValue);

        transactions.disjoin();

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


        TransactionItem transactionItem = new TransactionItem(transactionItemID,transactionID,date,quantity,
                                                                productID,productName,productPrice,guestID);
        this.getRecordArray().add(transactionItem);

        return true;
    }
}
