package com.xiaomi.dao;



import com.xiaomi.bean.Store;
import com.xiaomi.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreDao {
    public static Store getStoreListByStoreId(int StoreId) {
        Store mStore = null;
        String sql = "select * from store where StoreId=" + StoreId;
        ResultSet resultSet = DBUtils.getResultSet(sql);
        try {

            while (resultSet.next()) {
                mStore = new Store(resultSet.getInt("storeId"), resultSet.getString("storeName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return mStore;
        }
        return mStore;
    }

    public static void main(String[] args) {
        Store storeListByStoreId = StoreDao.getStoreListByStoreId(1);
        System.out.println(storeListByStoreId.toString());
    }
}
