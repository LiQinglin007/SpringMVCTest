package com.xiaomi.dao;


import com.google.gson.Gson;
import com.xiaomi.bean.AppStore;
import com.xiaomi.bean.Store;
import com.xiaomi.db.DBtools;
import com.xiaomi.utils.DBUtils;
import com.xiaomi.utils.PageBean;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDao {
    static SqlSession sqlSession = null;
    static IStoreDao mIstoreDao = null;

    private static IStoreDao getmIstoreDao() {
        if (sqlSession == null) {
            try {
                sqlSession = DBtools.getSqlSession();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mIstoreDao == null) {
            mIstoreDao = sqlSession.getMapper(IStoreDao.class);
        }
        return mIstoreDao;
    }

    public static AppStore getStoreListByStoreId(int StoreId) {
        AppStore mStore = null;
        Store store = getmIstoreDao().selectStoreById(StoreId);
        mStore = new AppStore();
        mStore.setStoreName(store.getStoreName());
        mStore.setStoreId(store.getStoreId());

//        String sql = "select * from store where StoreId=" + StoreId;
//        ResultSet resultSet = DBUtils.getResultSet(sql);
//        try {
//            while (resultSet.next()) {
//                mStore = new AppStore();
//                mStore.setStoreId(resultSet.getInt("storeId"));
//                mStore.setStoreName(resultSet.getString("storeName"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return mStore;
//        }
        return mStore;
    }

    public static List<Store> loadAll() {
        List<Store> mList = getmIstoreDao().selectAll();
        return mList;
    }

    public static List<Store> loadListByPageAndSize(int page, int size) {
        List<Store> mList = getmIstoreDao().selectByPageInterceptor(new PageBean((page - 1) * size, size));
        return mList;
    }

    /**
     * 使用selectOne方法的时候  如果数据库里边有一条以上记录  就会报错
     *
     * @param storeName
     * @return
     */
    public static Store getStoreByName(String storeName) {
        Store store = getmIstoreDao().selectStoreByName(storeName);
        return store;
    }


    public static void main(String[] args) {
        Store mStore = getStoreByName("小米之家");
        if (mStore != null) {
            System.out.println("a:" + new Gson().toJson(mStore));
        }

//        AppStore storeListByStoreId = getStoreListByStoreId(1);
//        if (storeListByStoreId != null) {
//            System.out.println("a:" + new Gson().toJson(storeListByStoreId));
//        }

//        List<Store> stores = loadAll();
//        if (stores != null && stores.size() != 0) {
//            for (Store store : stores) {
//                System.out.println("a:" + new Gson().toJson(store));
//            }
//        }
    }
}
