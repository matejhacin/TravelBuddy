package com.matejhacin.travelbuddy.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.matejhacin.travelbuddy.R;
import com.matejhacin.travelbuddy.adapters.CustomRecyclerAdapter;
import com.matejhacin.travelbuddy.classes.Trip;
import com.matejhacin.travelbuddy.database.DatabaseHandler;
import com.matejhacin.travelbuddy.database.DatabaseManager;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    /*
    Variables
     */

    private RecyclerView recyclerView;

    private CustomRecyclerAdapter customRecyclerAdapter;

    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Trip> tripArrayList;

    /*
    Lifecycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        // Set up RecyclerView
        recyclerView.setLayoutManager(layoutManager);

        // Set listeners
        findViewById(R.id.floatingActionButton).setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Initialize arraylist
        tripArrayList = new ArrayList<>();

        // Get trips from DB and add them to arraylist
        Cursor cursor = new DatabaseHandler(getApplicationContext()).getAllTrips();
        if (cursor.moveToFirst()) {
            do {
                Trip trip = new Trip(
                        cursor.getString(cursor.getColumnIndex(DatabaseManager.TRIP_CITY)),
                        cursor.getString(cursor.getColumnIndex(DatabaseManager.TRIP_COUNTRY)),
                        cursor.getString(cursor.getColumnIndex(DatabaseManager.TRIP_START_DATE)),
                        cursor.getString(cursor.getColumnIndex(DatabaseManager.TRIP_END_DATE))
                );
                tripArrayList.add(trip);
            } while (cursor.moveToNext());
        }

        // Populate/update RecyclerView
        customRecyclerAdapter = new CustomRecyclerAdapter(getApplicationContext(), tripArrayList);
        recyclerView.setAdapter(customRecyclerAdapter);
        customRecyclerAdapter.notifyDataSetChanged();
    }

    /*
    Callbacks
     */

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floatingActionButton) {
            startActivity(new Intent(MainActivity.this, AddTripActivity.class));
            overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        }
    }
}
