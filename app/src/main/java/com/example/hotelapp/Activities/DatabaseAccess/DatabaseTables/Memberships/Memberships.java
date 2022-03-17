package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Memberships;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;

public class Memberships extends DatabaseTable {
    public Memberships(DatabaseController databaseController) {
        super(databaseController);
        setRetrievalField(this.getPrimaryKey());
    }

    @Override
    public String getTableName() {
        return "memberships";
    }

    @Override
    public String getPrimaryKey() {
        return "membershipID";
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        int membershipID = results.getInt("membershipID");
        String membershipType = results.getString("membershipType");
        double discountPercent = results.getDouble("discountPercent");

        Membership membership = new Membership(membershipID, membershipType, discountPercent);
        return this.getRecordArray().add(membership);
    }
}
