package com.matejhacin.travelbuddy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.matejhacin.travelbuddy.R;
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

        public ViewHolder(View v) {
            super(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO something happens when clicked
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
    }
    /**
     * End of inner class
     */

    /*
    Variables
     */

    private ArrayList<Trip> tripArrayList;

    /*
    Constructor
     */

    public CustomRecyclerAdapter(ArrayList<Trip> tripArrayList) {
        this.tripArrayList = tripArrayList;
    }

    /*
    Callback methods
     */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getCityTextView().setText(tripArrayList.get(position).getCity());
        holder.getCountryTextView().setText(tripArrayList.get(position).getCountry());
        holder.getDateTextView().setText(tripArrayList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return tripArrayList.size();
    }
}
