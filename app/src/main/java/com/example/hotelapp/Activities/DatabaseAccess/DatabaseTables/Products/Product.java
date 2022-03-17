package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Products;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTableRecord;

public class Product extends DatabaseTableRecord {
    private int productID;
    private String productName;
    private double productPrice;
    private String productDescription;
    private String productType;
    public Product(int productID, String productName, double productPrice, String productDescription, String productType){
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productType = productType;
    }
    @Override
    public int getRecordId() {
        return this.productID;
    }

    @Override
    public String getAbbreviatedInfo() {
        String info =   "Product: " + this.productName +
                "\nPrice: " + this.productPrice;

        return info;
    }

    @Override
    public String getDetailedInfo() {
        String info =   "Product: " + this.productName +
                "\nPrice: " + this.productPrice +
                "\nDescription: " + this.productDescription;

        return info;
    }

    @Override
    public String getFieldValue(String fieldName) {
        return null;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getProductType() {
        return productType;
    }
}
