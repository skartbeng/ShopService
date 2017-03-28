package com.satkuru.retailmanager.repository;

import com.satkuru.retailmanager.model.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by karthi on 28/03/2017.
 */
public class ShopRepositoryImpl implements ShopRepository {
    private Map<String, Shop> shops = new ConcurrentHashMap<>();

    @Override
    public Shop addShop(Shop shop) {
        return shops.put(shop.getName(), shop);
    }

    @Override
    public List<Shop> getShops() {
        return new ArrayList<Shop>(shops.values());
    }

    @Override
    public Shop getShop(String shopName) {
        return shops.get(shopName);
    }
}
