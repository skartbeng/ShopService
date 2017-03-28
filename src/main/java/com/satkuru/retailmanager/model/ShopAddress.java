package com.satkuru.retailmanager.model;

/**
 * Created by karthi on 28/03/2017.
 */
public class ShopAddress {
    public ShopAddress() {
    }

    public ShopAddress(String number, String postCode) {
        this.number = number;
        this.postCode = postCode;
    }

    private String number;

    private String postCode;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "ShopAddress [number=" + number + ", postCode=" + postCode + "]";
    }
}
