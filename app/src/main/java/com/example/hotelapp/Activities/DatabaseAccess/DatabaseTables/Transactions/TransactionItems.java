package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Transactions;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Products.Products;
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
        return "transactionID";
    }

    @Override
    public boolean retrieve(String retrievalValue){
        TransactionItems transactionItems= new TransactionItems(this.databaseController);
        Products products = new Products(this.databaseController);
        transactionItems.joinWithTable(products, "productID");
        boolean success = super.retrieve(retrievalValue);
        disjoin();
        return success;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        while(results.next()){
            String productName = results.getString("productName");
            double price = results.getDouble("price");
            int quantity = results.getInt("quantity");
            TransactionItem transactionItem = new TransactionItem(productName, price, quantity);
            this.getRecordArray().add(transactionItem);
        }
        return true;
    }
}
