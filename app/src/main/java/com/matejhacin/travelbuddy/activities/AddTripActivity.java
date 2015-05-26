package com.matejhacin.travelbuddy.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.matejhacin.travelbuddy.R;
import com.matejhacin.travelbuddy.classes.Trip;
import com.matejhacin.travelbuddy.database.DatabaseHandler;
import com.rengwuxian.materialedittext.MaterialEditText;


public class AddTripActivity extends ActionBarActivity implements View.OnClickListener {

    /*
    Variables
     */

    private MaterialEditText countryEditText;
    private MaterialEditText cityEditText;
    private MaterialEditText startDateEditText;
    private MaterialEditText endDateEditText;

    /*
    Lifecycle
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        // Initialize variables
        countryEditText = (MaterialEditText) findViewById(R.id.countryEditText);
        cityEditText = (MaterialEditText) findViewById(R.id.cityEditText);
        startDateEditText = (MaterialEditText) findViewById(R.id.startDateEditText);
        endDateEditText = (MaterialEditText) findViewById(R.id.endDateEditText);

        // Set listeners
        findViewById(R.id.addTripButton).setOnClickListener(this);
    }

    /*
    Callbacks
     */

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addTripButton) {
            // Make sure all EditTexts are not empty
            if (editTextsNotEmpty()) {
                // Create trip from user input data
                Trip trip = new Trip(
                        cityEditText.getText().toString(),
                        countryEditText.getText().toString(),
                        startDateEditText.getText().toString(),
                        endDateEditText.getText().toString()
                );
                // Add trip to DB and close activity
                new DatabaseHandler(getApplicationContext()).addTrip(trip);
                finish();
            }
        }
    }

    /*
    Methods
     */

    private boolean editTextsNotEmpty() {
        if (countryEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Country empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cityEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "City empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (startDateEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Start date empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (endDateEditText.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "End date empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
