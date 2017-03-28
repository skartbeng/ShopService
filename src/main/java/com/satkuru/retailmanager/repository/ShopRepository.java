package com.satkuru.retailmanager.repository;

import com.satkuru.retailmanager.model.Shop;

import java.util.List;

/**
 * Created by karthi on 28/03/2017.
 */
public interface ShopRepository {
    public abstract Shop addShop( Shop shop );

    public abstract List<Shop> getShops();

    public Shop getShop(String shopName);
}
