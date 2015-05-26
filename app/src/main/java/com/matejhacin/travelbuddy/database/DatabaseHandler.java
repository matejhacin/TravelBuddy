package com.matejhacin.travelbuddy.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.matejhacin.travelbuddy.classes.DestinationMarker;
import com.matejhacin.travelbuddy.classes.Trip;

public class DatabaseHandler {

    /*
    Variables
     */

    private DatabaseManager dbManager;
    private SQLiteDatabase sqLiteDatabase;

    /*
    Constructor
     */

    public DatabaseHandler(Context context) {
        dbManager = new DatabaseManager(context);
    }

    /*
    Methods
     */

    private void open() {
        sqLiteDatabase = dbManager.getWritableDatabase();
    }

    private void close() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
    }

    /**
     * INSERTING
     */

    public int addTrip(Trip trip) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseManager.TRIP_CITY, trip.getCity());
        cv.put(DatabaseManager.TRIP_COUNTRY, trip.getCountry());
        cv.put(DatabaseManager.TRIP_START_DATE, trip.getStartDate());
        cv.put(DatabaseManager.TRIP_END_DATE, trip.getEndDate());

        open();
        return (int) sqLiteDatabase.insert(DatabaseManager.TRIP_TABLE_NAME, null, cv);
    }

    public int addMarker(DestinationMarker destinationMarker) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseManager.MARKER_TRIP_ID, destinationMarker.getT_id());
        cv.put(DatabaseManager.MARKER_TITLE, destinationMarker.getTitle());
        cv.put(DatabaseManager.MARKER_ADDRESS, destinationMarker.getAddress());
        cv.put(DatabaseManager.MARKER_INFO, destinationMarker.getInfo());
        cv.put(DatabaseManager.MARKER_LAT, destinationMarker.getLatitude());
        cv.put(DatabaseManager.MARKER_LONG, destinationMarker.getLongitude());
        cv.put(DatabaseManager.MARKER_STATUS, destinationMarker.getStatus());

        open();
        return (int) sqLiteDatabase.insert(DatabaseManager.MARKER_TABLE_NAME, null, cv);
    }

    /**
     * READING
     */

    public Cursor getAllTrips() {
        String query = "SELECT * FROM " + DatabaseManager.TRIP_TABLE_NAME + ";";
        open();
        return sqLiteDatabase.rawQuery(query, null);
    }

    public Cursor getAllMarkers(Trip trip) {
        String query = "SELECT * FROM " + DatabaseManager.MARKER_TABLE_NAME
                + " WHERE " + DatabaseManager.MARKER_TRIP_ID + " = " + trip.getId() + ";";
        open();
        return sqLiteDatabase.rawQuery(query, null);
    }

    /**
     * MODIFYING
     */

    public void setMarkerDone(DestinationMarker destinationMarker) {
        String query = "UPDATE " + DatabaseManager.MARKER_TABLE_NAME
                + " SET " + DatabaseManager.MARKER_STATUS + " = " + DatabaseManager.MARKER_STATUS_INACTIVE
                + " WHERE " + DatabaseManager.MARKER_ID + " = " + destinationMarker.getId() + ";";
        open();
        sqLiteDatabase.rawQuery(query, null);
    }
}
