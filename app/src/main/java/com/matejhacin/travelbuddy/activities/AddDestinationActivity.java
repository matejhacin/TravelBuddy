package com.matejhacin.travelbuddy.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.matejhacin.travelbuddy.R;
import com.matejhacin.travelbuddy.classes.DestinationMarker;
import com.matejhacin.travelbuddy.classes.Trip;
import com.matejhacin.travelbuddy.database.DatabaseHandler;
import com.matejhacin.travelbuddy.database.DatabaseManager;


public class AddDestinationActivity extends ActionBarActivity implements View.OnClickListener{

    /*
    Variables
     */

    private EditText titleEditText;
    private EditText addressEditText;
    private EditText infoEditText;
    private EditText latEditText;
    private EditText longEditText;

    private Trip trip;

    /*
    Lifecycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_destination);

        // Get extra
        trip = (Trip) getIntent().getExtras().get("trip");

        // Initialize variables
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        addressEditText = (EditText) findViewById(R.id.addressEditText);
        infoEditText = (EditText) findViewById(R.id.infoEditText);
        latEditText = (EditText) findViewById(R.id.latEditText);
        longEditText = (EditText) findViewById(R.id.longEditText);

        // Listener
        findViewById(R.id.addDestinationButton).setOnClickListener(this);
    }

    /*
    Callbacls
     */

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addDestinationButton) {
            if (editTextsNotEmpty()) {
                // Create new destination marker
                DestinationMarker destinationMarker = new DestinationMarker(
                        trip.getId(),
                        titleEditText.getText().toString(),
                        addressEditText.getText().toString(),
                        infoEditText.getText().toString(),
                        Double.parseDouble(latEditText.getText().toString()),
                        Double.parseDouble(longEditText.getText().toString()),
                        DatabaseManager.MARKER_STATUS_ACTIVE
                );
                // Add it to Database
                new DatabaseHandler(getApplicationContext()).addMarker(destinationMarker);
                finish();
            }
        }
    }

    /*
    Methods
     */

    private boolean editTextsNotEmpty() {
        if (titleEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Title empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (addressEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Address empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (latEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Latitude empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (longEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Longitude empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
