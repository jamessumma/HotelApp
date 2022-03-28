package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guests;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Products.Products;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
        TransactionItems transactionItems = new TransactionItems(this.databaseController);
        Products products = new Products(this.databaseController);
        joinWithTable(guests, "guestID");
        joinWithTable(transactionItems, "transactionID");
        transactionItems.joinWithTable(products, "productID");
        boolean success = super.retrieve(retrievalValue);
        disjoin();
        return success;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        int transactionID = results.getInt("transactionID");
        Date date = results.getDate("transactionDate");
        String userName = results.getString("userName");
        String name = results.getString("firstName") + " " + results.getString("lastName");
        ArrayList<TransactionItem> items = new ArrayList<>();
        do{
            String itemName = results.getString("productName");
            double price = results.getDouble("productPrice");
            int quantity = results.getInt("quantity");
            items.add(new TransactionItem(itemName, price, quantity));
        }while(results.next());
        Transaction transaction = new Transaction(transactionID, userName, name, date, items);
        return this.getRecordArray().add(transaction);
    }
}
