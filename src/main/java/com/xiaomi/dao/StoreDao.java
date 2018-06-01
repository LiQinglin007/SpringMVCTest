package com.xiaomi.dao;


import com.google.gson.Gson;
import com.xiaomi.bean.AppStore;
import com.xiaomi.bean.Store;
import com.xiaomi.db.DBtools;
import com.xiaomi.utils.PageBean;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StoreDao {


    public static AppStore getStoreListByStoreId(int StoreId) {
        AppStore mStore = null;
        SqlSession sqlSession = null;
        Store store = null;
        try {
            sqlSession = DBtools.getSqlSessionFactory().openSession();
            store = sqlSession.getMapper(IStoreDao.class).selectStoreById(StoreId);
            mStore = new AppStore();
            mStore.setStoreName(store.getStoreName());
            mStore.setStoreId(store.getStoreId());
            sqlSession.commit();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
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
        SqlSession sqlSession = null;
        List<Store> mList = new ArrayList<>();
        try {
            sqlSession = DBtools.getSqlSessionFactory().openSession();
            mList =sqlSession.getMapper(IStoreDao.class).selectAll();
            sqlSession.commit();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return mList;
    }

    public static List<Store> loadListByPageAndSize(int page, int size) {
        SqlSession sqlSession = null;
        List<Store> mList = new ArrayList<>();
        try {
            sqlSession = DBtools.getSqlSessionFactory().openSession();
            mList =sqlSession.getMapper(IStoreDao.class).selectByPageInterceptor(new PageBean((page - 1) * size, size));
            sqlSession.commit();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
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
        SqlSession sqlSession = null;
        Store store = null;
        try {
            sqlSession = DBtools.getSqlSessionFactory().openSession();
            store =sqlSession.getMapper(IStoreDao.class).selectStoreByName(storeName);
            sqlSession.commit();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
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
