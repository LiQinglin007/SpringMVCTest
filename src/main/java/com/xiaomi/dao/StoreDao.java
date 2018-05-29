package com.xiaomi.dao;


import com.google.gson.Gson;
import com.xiaomi.bean.AppStore;
import com.xiaomi.bean.Store;
import com.xiaomi.db.DBtools;
import com.xiaomi.utils.DBUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDao {
    static SqlSession sqlSession = null;

    public static AppStore getStoreListByStoreId(int StoreId) {
        AppStore mStore = null;
        Store bean = new Store();
        try {
            sqlSession = DBtools.getSqlSession();
            bean = sqlSession.selectOne("Store.selectStoreById", StoreId);
            mStore = new AppStore();
            mStore.setStoreName(bean.getStoreName());
            mStore.setStoreId(bean.getStoreId());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }

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
        List<Store> mList = new ArrayList<>();
        try {
            sqlSession = DBtools.getSqlSession();
            mList = sqlSession.selectList("Store.selectAll");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return mList;
    }

    /**
     * 使用selectOne方法的时候  如果数据库里边有一条以上记录  就会报错
     *
     * @param storeName
     * @return
     */
    public static Store getStoreByName(String storeName) {
        Store store = null;
        try {
            sqlSession = DBtools.getSqlSession();
            store = sqlSession.selectOne("Store.selectStoreByName", storeName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return store;
    }


    public static List<AppStore> getAppStoreByStoreId(String storeId) {
        List<AppStore> mList = new ArrayList<>();
        try {
            sqlSession = DBtools.getSqlSession();
            mList = sqlSession.selectList("AppStore.selectAppStoreByStoreId", storeId);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return mList;
    }


    public static List<AppStore> getAppStoreByUserId(String userId) {
        List<AppStore> mList = new ArrayList<>();
        try {
            sqlSession = DBtools.getSqlSession();
            mList = sqlSession.selectList("AppStore.selectAppStoreByUserId", userId);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return mList;
    }

    public static void main(String[] args) {
        List<AppStore> appStoreByUserId = getAppStoreByUserId("1");
        if (appStoreByUserId != null && appStoreByUserId.size() != 0) {
            System.out.println(new Gson().toJson(appStoreByUserId));
        }

//        List<AppStore> appStoreByStoreId = getAppStoreByStoreId("1");
//        if (appStoreByStoreId != null && appStoreByStoreId.size() != 0) {
//            System.out.println(new Gson().toJson(appStoreByStoreId));
//        }

//        Store mStore = getStoreByName("小米之家");
//        if (mStore != null) {
//            System.out.println("a:" + new Gson().toJson(mStore));
//        }

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
