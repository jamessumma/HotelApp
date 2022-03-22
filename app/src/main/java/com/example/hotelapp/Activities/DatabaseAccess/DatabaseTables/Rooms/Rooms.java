package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Rooms;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.RoomTypes.RoomTypes;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;

public class Rooms extends DatabaseTable {
    public Rooms(DatabaseController databaseController) {
        super(databaseController);
        setRetrievalField(this.getPrimaryKey());
    }

    @Override
    public String getTableName() {
        return "rooms";
    }

    @Override
    public String getPrimaryKey() {
        return "roomID";
    }

    @Override
    public boolean retrieve(String retrievalValue){
        RoomTypes roomTypes = new RoomTypes(this.databaseController);
        joinWithTable(roomTypes, "roomTypeID");
        boolean success = super.retrieve(retrievalValue);
        disjoin();
        return success;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        int roomID = results.getInt("roomID");
        String description = results.getString("description");
        int floor = results.getInt("floor");
        double price = results.getDouble("price");

        Room room = new Room(roomID, description, floor, price);
        return this.getRecordArray().add(room);
    }
}
