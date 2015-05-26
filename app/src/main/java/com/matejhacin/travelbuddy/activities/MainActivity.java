package com.matejhacin.travelbuddy.activities;

import android.content.Intent;
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
        tripArrayList = new ArrayList<>();

        // Set up RecyclerView
        recyclerView.setLayoutManager(layoutManager);

        // Set listeners
        findViewById(R.id.floatingActionButton).setOnClickListener(this);

        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("Trbovlje", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("Ljubljana", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));
        tripArrayList.add(new Trip("London", "UK", "Jan 12\nJan 18"));

        customRecyclerAdapter = new CustomRecyclerAdapter(tripArrayList);
        recyclerView.setAdapter(customRecyclerAdapter);

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
