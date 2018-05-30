package com.xiaomi.dao;

import com.xiaomi.bean.AppStore;

import java.util.List;

public interface IAppStoreDao {
    List<AppStore> selectAppStoreByStoreId(String storeId);
    List<AppStore> selectAppStoreByUserId(String userId);
}
