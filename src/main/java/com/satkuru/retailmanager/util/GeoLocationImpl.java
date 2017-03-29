package com.satkuru.retailmanager.util;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.satkuru.retailmanager.model.LatitudeLongitude;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by karthi on 28/03/2017.
 */
public class GeoLocationImpl {
    public LatitudeLongitude getLatLng(String apiKey, String number, String postcode ) throws ApiException, InterruptedException, IOException
    {
        GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
        Optional<GeocodingResult[]> results = Optional.ofNullable(GeocodingApi.geocode(context, number + "," + postcode).await());

        if(results.isPresent() &&results.get().length>0) {
            Geometry geometry = results.get()[0].geometry;
            return new LatitudeLongitude(geometry.location.lat,geometry.location.lng);
        }
        else{
            throw new IllegalArgumentException("No valid GeoLocation found for the address");
        }
    }

    public double findDistance(LatitudeLongitude from, LatitudeLongitude to){
        double fromLat = from.lat;
        double fromLng = from.lng;
        double toLat = to.lat;
        double toLng = to.lng;
        double earthRadius = 6371000;//in meters

        double dLat = Math.toRadians(toLat-fromLat);
        double dLng = Math.toRadians(toLng-fromLng);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(fromLat)) *
                        Math.cos(Math.toRadians(toLat)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (earthRadius*c);
        return dist;

    }
}
