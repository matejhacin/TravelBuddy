package com.matejhacin.travelbuddy.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.matejhacin.travelbuddy.R;
import com.matejhacin.travelbuddy.classes.DestinationMarker;
import com.matejhacin.travelbuddy.classes.Trip;
import com.matejhacin.travelbuddy.database.DatabaseHandler;
import com.matejhacin.travelbuddy.database.DatabaseManager;

import java.util.ArrayList;
import java.util.HashMap;


public class MapViewActivity extends ActionBarActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    /*
    Variables
     */

    private Trip trip;
    private HashMap<MarkerOptions, DestinationMarker> markerOptionsHashMap;
    private HashMap<Marker, DestinationMarker> markerHashMap;

    /*
    Lifecycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        // Initialize variables
        trip = (Trip) getIntent().getExtras().get("trip");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Initialize variables
        markerOptionsHashMap = new HashMap<>();

        // Get all markers from DB
        Cursor cursor = new DatabaseHandler(getApplicationContext()).getAllMarkers(trip);
        if (cursor.moveToFirst()) {
            do {
                DestinationMarker destinationMarker = new DestinationMarker(
                        cursor.getInt(cursor.getColumnIndex(DatabaseManager.MARKER_ID)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseManager.MARKER_TRIP_ID)),
                        cursor.getString(cursor.getColumnIndex(DatabaseManager.MARKER_TITLE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseManager.MARKER_ADDRESS)),
                        cursor.getString(cursor.getColumnIndex(DatabaseManager.MARKER_INFO)),
                        cursor.getDouble(cursor.getColumnIndex(DatabaseManager.MARKER_LAT)),
                        cursor.getDouble(cursor.getColumnIndex(DatabaseManager.MARKER_LONG)),
                        cursor.getInt(cursor.getColumnIndex(DatabaseManager.MARKER_STATUS))
                );
                MarkerOptions markerOptions = new MarkerOptions().position(destinationMarker.getLatLng()).title(destinationMarker.getTitle());
                // Add both to HashMap
                markerOptionsHashMap.put(markerOptions, destinationMarker);
            } while (cursor.moveToNext());
        }

        // Get map
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /*
    Callbacks
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addMarkerAction) {
            // Start new activity and send current Trip object to it
            Intent intent = new Intent(MapViewActivity.this, AddDestinationActivity.class);
            intent.putExtra("trip", trip);
            startActivity(intent);
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    Map
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Initialize HashMap
        markerHashMap = new HashMap<>();
        // Add all markers to map
        for (MarkerOptions markerOptions : markerOptionsHashMap.keySet()) {
            // Create marker and add it to new HashMap with markers
            Marker marker = googleMap.addMarker(markerOptions);
            markerHashMap.put(marker, markerOptionsHashMap.get(markerOptions));
        }
        // Set marker click listener
        googleMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(MapViewActivity.this, DestinationInfoActivity.class);
        intent.putExtra("marker", markerHashMap.get(marker));
        startActivity(intent);
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }
}
