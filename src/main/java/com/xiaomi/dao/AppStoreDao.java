package com.xiaomi.dao;

import com.xiaomi.bean.AppStore;
import com.xiaomi.db.DBtools;
import org.apache.ibatis.session.SqlSession;


import java.util.ArrayList;
import java.util.List;

public class AppStoreDao {


    public static List<AppStore> getAppStoreByStoreId(String storeId) {
        SqlSession sqlSession = null;
        List<AppStore> appStores = new ArrayList<>();
        try {
            sqlSession = DBtools.getSqlSessionFactory().openSession();
            appStores = sqlSession.getMapper(IAppStoreDao.class).selectAppStoreByStoreId(storeId);
            sqlSession.commit();
        }  finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return appStores;
    }

    public static List<AppStore> getAppStoreByUserId(String userId) {
        SqlSession sqlSession = null;
        List<AppStore> appStores = new ArrayList<>();
        try {
            sqlSession = DBtools.getSqlSessionFactory().openSession();
            appStores = sqlSession.getMapper(IAppStoreDao.class).selectAppStoreByUserId(userId);
            sqlSession.commit();
        }  finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return appStores;
    }

    public static void main(String[] args) {
        List<AppStore> appStoreByStoreId = getAppStoreByStoreId("1");

    }
}
