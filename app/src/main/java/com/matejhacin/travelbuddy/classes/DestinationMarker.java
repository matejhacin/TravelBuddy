package com.matejhacin.travelbuddy.classes;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class DestinationMarker implements Serializable {

    /*
    Variables
     */

    private int id;
    private int t_id;
    private String title;
    private String address;
    private String info;
    private double latitude;
    private double longitude;
    private int status;

    /*
    Constructor
     */

    // With id - when reading from DB
    public DestinationMarker(int id, int t_id, String title, String address, String info, double latitude, double longitude, int status) {
        this.id = id;
        this.t_id = t_id;
        this.title = title;
        this.address = address;
        this.info = info;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    // Without id - when inserting to DB
    public DestinationMarker(int t_id, String title, String address, String info, double latitude, double longitude, int status) {
        this.t_id = t_id;
        this.title = title;
        this.address = address;
        this.info = info;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    /*
    Getters
     */

    public int getId() {
        return id;
    }

    public int getT_id() {
        return t_id;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getInfo() {
        return info;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }

    public int getStatus() {
        return status;
    }

    public boolean isActive() {
        return status == 1;
    }
}
