package com.xiaomi.dao;

import com.xiaomi.bean.Store;
import com.xiaomi.utils.PageBean;

import java.util.List;

public interface IStoreDao {
    Store selectStoreById(int storeId);

    List<Store> selectAll();

    List<Store> selectByPageInterceptor(PageBean mPageBean);

    Store selectStoreByName(String storeName);

    List<Store> selectAppStoreByStoreId(String storeId);


}
