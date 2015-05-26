package com.matejhacin.travelbuddy.classes;

public class Trip {

    /*
    Variables
     */

    private int id;
    private String city;
    private String country;
    private String startDate;
    private String endDate;

    /*
    Constructor
     */

    public Trip (int id, String city, String country, String startDate, String endDate) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /*
    Getters
     */

    public int getId(){
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDate() {
        return startDate + "\n" + endDate;
    }
}
