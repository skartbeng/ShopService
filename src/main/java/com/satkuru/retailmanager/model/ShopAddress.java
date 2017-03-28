package com.satkuru.retailmanager.model;

/**
 * Created by karthi on 28/03/2017.
 */
public class ShopAddress {
    public ShopAddress() {
    }

    public ShopAddress(int number, String postCode) {
        this.number = number;
        this.postCode = postCode;
    }

    private int number;

    private String postCode;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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
