package com.xiaomi.dao;

import com.xiaomi.bean.AppStore;
import com.xiaomi.db.DBtools;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AppStoreDao {
    private static SqlSession sqlSession = null;
    private static IAppStoreDao mIAppStoreDao = null;

    public static IAppStoreDao getmIAppStoreDao() {
        if (sqlSession == null) {
            sqlSession = DBtools.getSqlSession();
        }
        if (mIAppStoreDao == null) {
            mIAppStoreDao = sqlSession.getMapper(IAppStoreDao.class);
        }
        return mIAppStoreDao;
    }

    public static List<AppStore> getAppStoreByStoreId(String storeId) {
        List<AppStore> mList = getmIAppStoreDao().selectAppStoreByStoreId(storeId);
        if (sqlSession != null) sqlSession.close();
        return mList;
    }

    public static List<AppStore> getAppStoreByUserId(String userId) {
        List<AppStore> mList = getmIAppStoreDao().selectAppStoreByUserId(userId);
        if (sqlSession != null) sqlSession.close();
        return mList;
    }
}
