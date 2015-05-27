package com.matejhacin.travelbuddy.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.matejhacin.travelbuddy.R;
import com.matejhacin.travelbuddy.classes.DestinationMarker;
import com.matejhacin.travelbuddy.database.DatabaseHandler;


public class DestinationInfoActivity extends ActionBarActivity implements View.OnClickListener {

    /*
    Variables
     */

    private TextView addressTextView;
    private TextView infoTextView;

    private DestinationMarker destinationMarker;

    /*
    Lifecycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_info);

        // Get extras
        destinationMarker = (DestinationMarker) getIntent().getExtras().get("marker");

        // Initialize variables
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        infoTextView = (TextView) findViewById(R.id.infoTextView);

        // Listener
        findViewById(R.id.markDoneButton).setOnClickListener(this);

        // Set text
        getSupportActionBar().setTitle(destinationMarker.getTitle());
        addressTextView.setText(destinationMarker.getAddress());
        infoTextView.setText(destinationMarker.getInfo());
    }

    /*
    Callbacks
     */

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.markDoneButton) {
            // Mark marker as done in DB
            new DatabaseHandler(getApplicationContext()).setMarkerDone(destinationMarker);
            finish();
        }
    }
}
