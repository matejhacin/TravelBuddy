package com.matejhacin.travelbuddy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    /*
    Variables
     */

    public static String DB_NAME = "TravelBuddy";
    public static int DB_VERSION = 1;

    /*
    Constructor
     */

    public DatabaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /*
    Table variables
     */

    // Trip table
    public static String TRIP_TABLE_NAME = "trip";
    public static String TRIP_ID = "id";
    public static String TRIP_CITY = "city";
    public static String TRIP_COUNTRY = "country";
    public static String TRIP_START_DATE = "start_date";
    public static String TRIP_END_DATE = "end_date";

    String tripQuery =
            "CREATE TABLE " + TRIP_TABLE_NAME + " ("
            + TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TRIP_CITY + " TEXT, "
            + TRIP_COUNTRY + " TEXT, "
            + TRIP_START_DATE + " TEXT, "
            + TRIP_END_DATE + " TEXT);";

    // Marker table
    public static String MARKER_TABLE_NAME = "marker";
    public static String MARKER_ID = "id";
    public static String MARKER_TRIP_ID = "t_id";
    public static String MARKER_TITLE = "title";
    public static String MARKER_ADDRESS = "address";
    public static String MARKER_INFO = "info";
    public static String MARKER_LAT = "latitude";
    public static String MARKER_LONG = "longitude";
    public static String MARKER_STATUS = "status";

    public static int MARKER_STATUS_INACTIVE = 0;
    public static int MARKER_STATUS_ACTIVE = 1;

    String markerQuery =
            "CREATE TABLE " + MARKER_TABLE_NAME + " ("
                    + MARKER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + MARKER_TRIP_ID + " INTEGER, "
                    + MARKER_TITLE + " TEXT, "
                    + MARKER_ADDRESS + " TEXT, "
                    + MARKER_INFO + " TEXT, "
                    + MARKER_LAT + " REAL, "
                    + MARKER_LONG + " REAL, "
                    + MARKER_STATUS + " INTEGER);";

    /*
    Lifecycle
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tripQuery);
        db.execSQL(markerQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRIP_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MARKER_TABLE_NAME);

        onCreate(db);
    }
}
