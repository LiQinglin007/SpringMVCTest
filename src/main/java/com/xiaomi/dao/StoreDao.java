package com.xiaomi.dao;


import com.xiaomi.bean.AppStore;
import com.xiaomi.bean.Store;
import com.xiaomi.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreDao {
    public static AppStore getStoreListByStoreId(int StoreId) {
        AppStore mStore = null;
        String sql = "select * from store where StoreId=" + StoreId;
        ResultSet resultSet = DBUtils.getResultSet(sql);
        try {
            while (resultSet.next()) {
                mStore = new AppStore();
                mStore.setStoreId(resultSet.getInt("storeId"));
                mStore.setStoreName(resultSet.getString("storeName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return mStore;
        }
        return mStore;
    }
}
