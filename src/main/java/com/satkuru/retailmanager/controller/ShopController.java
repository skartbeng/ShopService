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

        LatitudeLongitude latLng = new GeoLocationImpl().getLatLng( myConfig.getApiKey(), shop.getShopAddress().getNumber(),shop.getShopAddress().getPostCode());
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
    public Shop findNearest(@RequestParam(value = "longitude", required = true) String longitude, @RequestParam(value = "latitude", required = true) String latitude) {
        return null;
    }
}
