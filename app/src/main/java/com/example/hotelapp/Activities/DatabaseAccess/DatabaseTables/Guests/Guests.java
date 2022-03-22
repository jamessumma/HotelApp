package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Memberships.Membership;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Memberships.Memberships;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;
import java.util.Date;

public class Guests extends DatabaseTable {
    public Guests(DatabaseController databaseController) {
        super(databaseController);
        setRetrievalField(this.getPrimaryKey());
    }

    @Override
    public String getTableName() {
        return "guests";
    }

    @Override
    public String getPrimaryKey() {
        return "guestID";
    }

    @Override
    public boolean retrieve(String retrievalValue){
        Memberships memberships = new Memberships(this.databaseController);
        joinWithTable(memberships, "membershipID");
        boolean success = super.retrieve(retrievalValue);
        disjoin();
        return success;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        int guestId = results.getInt("guestID");
        String userName = results.getString("userName");
        String firstName = results.getString("firstName");
        String lastName = results.getString("lastName");
        Date dateOfBirth = results.getDate("dateOfBirth");
        String password = results.getString("password");

        int membershipID = results.getInt("membershipID");
        String membershipType = results.getString("membershipType");
        double discountPercent = results.getDouble("discountPercent");

        Membership membership = new Membership(membershipID, membershipType, discountPercent);


        Guest guest = new Guest(
                guestId,
                userName,
                firstName,
                lastName,
                dateOfBirth,
                password,
                membership
        );
        return this.getRecordArray().add(guest);
    }
}
