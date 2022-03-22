package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.RoomTypes;

import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;

public class RoomTypes extends DatabaseTable {
    public RoomTypes(DatabaseController databaseController) {
        super(databaseController);
        setRetrievalField(this.getPrimaryKey());
    }

    @Override
    public String getTableName() {
        return "room_types";
    }

    @Override
    public String getPrimaryKey() {
        return "roomTypeID";
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        int roomTypeID = results.getInt("roomTypeID");
        double price = results.getDouble("price");
        String description = results.getString("description");

        RoomType roomType = new RoomType(roomTypeID, price, description);
        return this.getRecordArray().add(roomType);
    }
}
