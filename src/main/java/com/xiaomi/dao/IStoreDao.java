package com.xiaomi.dao;

import com.xiaomi.bean.Store;

import java.util.List;

public interface IStoreDao {
    Store selectStoreById(int storeId);

    List<Store> selectAll();

    Store selectStoreByName(String storeName);

    List<Store> selectAppStoreByStoreId(String storeId);
}
