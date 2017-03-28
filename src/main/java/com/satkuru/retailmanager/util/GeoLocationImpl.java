package com.satkuru.retailmanager.util;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.satkuru.retailmanager.model.LatitudeLongitude;

import java.io.IOException;

/**
 * Created by karthi on 28/03/2017.
 */
public class GeoLocationImpl {
    public LatitudeLongitude getLatLng(String apiKey, int number, String postcode ) throws ApiException, InterruptedException, IOException
    {
        GeoApiContext context = new GeoApiContext().setApiKey(apiKey);
        GeocodingResult[] results =  GeocodingApi.geocode(context, number +"," +postcode).await();
        LatLng latLng = results[0].geometry.location;
        return new LatitudeLongitude(latLng.lat, latLng.lng);
    }
}
