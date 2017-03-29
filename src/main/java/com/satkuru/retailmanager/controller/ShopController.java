package com.satkuru.retailmanager.controller;

import com.google.maps.errors.ApiException;
import com.satkuru.retailmanager.AppConfig;
import com.satkuru.retailmanager.model.LatitudeLongitude;
import com.satkuru.retailmanager.model.Shop;
import com.satkuru.retailmanager.repository.ShopRepository;
import com.satkuru.retailmanager.util.GeoLocationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by karthi on 28/03/2017.
 */
@RestController
public class ShopController {
    @Autowired
    ShopRepository shops;

    @Autowired
    AppConfig myConfig;

    @RequestMapping("/")
    public String index() throws ApiException, InterruptedException, IOException {

        return "Greetings from Retail Manager! " + shops.getShops().toString();
    }


    @RequestMapping( path="/shops", method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Shop shop) throws ApiException, InterruptedException, IOException {
        LatitudeLongitude latLng = null;
        try{
            latLng = new GeoLocationImpl().getLatLng( myConfig.getApiKey(), shop.getShopAddress().getNumber(),shop.getShopAddress().getPostCode());
        }catch (Exception gex){
            return new ResponseEntity<Shop>(new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        shop.setLatitudeLongitude(latLng);
        Shop exist = shops.getShop(shop.getName());
        if(exist!=null){
            Shop replaced = shops.addShop(shop);
            return new ResponseEntity<Shop>(replaced,new HttpHeaders(), HttpStatus.OK);
        }
        else {
            Shop saved = shops.addShop(shop);
            return new ResponseEntity<Shop>(saved,new HttpHeaders(), HttpStatus.CREATED);
        }

    }

    @RequestMapping("/findNearest")
    public  ResponseEntity<?> findNearest(@RequestParam(value = "longitude", required = true) String longitude, @RequestParam(value = "latitude", required = true) String latitude) {
        LatitudeLongitude fromLocation = new LatitudeLongitude(Double.valueOf(longitude), Double.valueOf(latitude));
        List<Shop> shops = this.shops.getShops();
        Shop nearestShop = null;
        double shortestDistance = 0.00;
        boolean valueSet = false;
        for (Shop shop: shops ) {
            if(shop.getLatitudeLongitude()!=null){
                double distance = new GeoLocationImpl().findDistance(fromLocation, shop.getLatitudeLongitude());
                if(!valueSet){
                    shortestDistance=distance;
                    nearestShop=shop;
                    valueSet=true;
                    continue;
                }
                if(distance<=shortestDistance){
                    nearestShop=shop;
                    shortestDistance=distance;
                }
            }
        }
        if(nearestShop!=null){
            return new ResponseEntity<Shop>(nearestShop,new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<Shop>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

    }
}
