package com.matejhacin.travelbuddy.classes;

public class Trip {

    /*
    Variables
     */

    private String city;
    private String country;
    private String date;

    /*
    Constructor
     */

    public Trip (String city, String country, String date) {
        this.city = city;
        this.country = country;
        this.date = date;
    }

    /*
    Getters
     */

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }
}
