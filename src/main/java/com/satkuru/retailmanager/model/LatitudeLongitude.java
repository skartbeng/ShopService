package com.satkuru.retailmanager.model;

/**
 * Created by karthi on 28/03/2017.
 */
public class LatitudeLongitude {
    public final double lat;

    public final double lng;

    public LatitudeLongitude(double lat, double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "LatitudeLongitude [lat=" + lat + ", lng=" + lng + "]";
    }

}
