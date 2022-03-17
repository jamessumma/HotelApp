package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Products;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;

public class Products extends DatabaseTable {
    public Products(DatabaseController databaseController) {
        super(databaseController);
        setRetrievalField(this.getPrimaryKey());
    }

    @Override
    public String getTableName() {
        return "products";
    }

    @Override
    public String getPrimaryKey() {
        return "productID";
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        int productID = results.getInt("productID");
        String productName = results.getString("productName");
        double productPrice = results.getDouble("productPrice");
        String productDescription = results.getString("productDescription");
        String productType = results.getString("productType");

        Product product = new Product(productID, productName, productPrice, productDescription, productType);
        return this.getRecordArray().add(product);
    }
}
