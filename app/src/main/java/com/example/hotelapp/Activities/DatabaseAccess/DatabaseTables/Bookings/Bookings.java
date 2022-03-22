package com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Bookings;

import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guest;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Guests.Guests;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Memberships.Membership;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Memberships.Memberships;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.RoomTypes.RoomTypes;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Rooms.Room;
import com.example.hotelapp.Activities.DatabaseAccess.DatabaseTables.Rooms.Rooms;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.DatabaseController;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseAccess.QueryResults;
import com.example.myandroidsupportlibrary.DatabaseSupport.DatabaseTable.DatabaseTable;

import java.sql.SQLException;
import java.util.Date;

public class Bookings extends DatabaseTable {
    public Bookings(DatabaseController databaseController) {
        super(databaseController);
        setRetrievalField(this.getPrimaryKey());
    }

    @Override
    public String getTableName() {
        return "bookings";
    }

    @Override
    public String getPrimaryKey() {
        return "bookingID";
    }

    @Override
    public boolean retrieve(String retrievalValue){
        Guests guests = new Guests(this.databaseController);
        Rooms rooms = new Rooms(this.databaseController);
        RoomTypes roomTypes = new RoomTypes(this.databaseController);
        Memberships memberships = new Memberships(this.databaseController);

        joinWithTable(guests, "guestID");
        joinWithTable(rooms, "roomID");
        rooms.joinWithTable(roomTypes, "roomTypeID");
        guests.joinWithTable(memberships, "memberShipID");

        boolean success = super.retrieve(retrievalValue);
        disjoin();
        return success;
    }

    @Override
    protected boolean addCurrentResult(QueryResults results) throws SQLException {
        int bookingID = results.getInt("bookingID");
        Date startDate = results.getDate("startDate");
        int duration = results.getInt("duration");

        int roomID = results.getInt("roomID");
        String roomType = results.getString("description");
        int floor = results.getInt("floor");
        double price = results.getDouble("price");
        
        String firstName = results.getString("firstName");
        String lastName = results.getString("lastName");

        double discountPercent = results.getDouble("discountPercent");

        price = (price*(1-discountPercent))*duration;

        Booking booking = new Booking(bookingID, firstName, lastName, roomID,
                floor, roomType, startDate, duration, price);

        return this.getRecordArray().add(booking);
    }
}
