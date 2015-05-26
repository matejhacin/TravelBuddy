package com.matejhacin.travelbuddy.adapters;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.matejhacin.travelbuddy.R;
import com.matejhacin.travelbuddy.activities.MapViewActivity;
import com.matejhacin.travelbuddy.classes.Trip;

import java.util.ArrayList;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {

    /**
     * Inner class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView cityTextView;
        private final TextView countryTextView;
        private final TextView dateTextView;
        private Trip trip;
        private Context context;

        public ViewHolder(View v, final Context context) {
            super(v);

            this.context = context;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MapViewActivity.class);
                    intent.putExtra("trip", trip);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });

            cityTextView = (TextView) v.findViewById(R.id.cityTextView);
            countryTextView = (TextView) v.findViewById(R.id.countryTextView);
            dateTextView = (TextView) v.findViewById(R.id.dateTextView);
        }

        public TextView getCityTextView () {
            return cityTextView;
        }

        public TextView getCountryTextView () {
            return countryTextView;
        }

        public TextView getDateTextView () {
            return dateTextView;
        }

        public void setTrip (Trip trip) {
            this.trip = trip;
        }
    }
    /**
     * End of inner class
     */

    /*
    Variables
     */

    private ArrayList<Trip> tripArrayList;
    private Context context;

    /*
    Constructor
     */

    public CustomRecyclerAdapter(Context context, ArrayList<Trip> tripArrayList) {
        this.tripArrayList = tripArrayList;
        this.context = context;
    }

    /*
    Callback methods
     */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_item, parent, false);
        return new ViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getCityTextView().setText(tripArrayList.get(position).getCity());
        holder.getCountryTextView().setText(tripArrayList.get(position).getCountry());
        holder.getDateTextView().setText(tripArrayList.get(position).getDate());
        holder.setTrip(tripArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return tripArrayList.size();
    }
}
