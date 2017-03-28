package com.satkuru.retailmanager.model;

/**
 * Created by karthi on 28/03/2017.
 */
public class Shop {

    public Shop(){

    }

    public Shop(String name,ShopAddress shopAddress) {
        this.name = name;
        this.shopAddress = shopAddress;
    }

    private String name;

    private ShopAddress shopAddress;

    private LatitudeLongitude latitudeLongitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShopAddress getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(ShopAddress shopAddress) {
        this.shopAddress = shopAddress;
    }

    public LatitudeLongitude getLatitudeLongitude() {
        return latitudeLongitude;
    }

    public void setLatitudeLongitude(LatitudeLongitude latitudeLongitude) {
        this.latitudeLongitude = latitudeLongitude;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", shopAddress=" + shopAddress +
                ", latitudeLongitude=" + latitudeLongitude +
                '}';
    }
}
