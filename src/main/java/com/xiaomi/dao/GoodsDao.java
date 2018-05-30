package com.xiaomi.dao;


import com.google.gson.Gson;
import com.xiaomi.bean.Goods;
import com.xiaomi.db.DBtools;
import com.xiaomi.utils.DBUtils;
import com.xiaomi.utils.PageBean;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
    private static SqlSession sqlSession = null;
    private static IGoodsDao mIGoodsDao = null;

    public static IGoodsDao getmIGoodsDao() {
        if (sqlSession == null) {
            try {
                sqlSession = DBtools.getSqlSession();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mIGoodsDao == null) {
            mIGoodsDao = sqlSession.getMapper(IGoodsDao.class);
        }
        return mIGoodsDao;
    }

    /**
     * 通过用户id和店铺id查询购物车里边该商店的全部商品
     *
     * @return
     */
    public static List<Goods> getGoodListByUserId(String UserId) {
//        List<Goods> mGoodsList = new ArrayList<>();
//        String sql = " SELECT * FROM goods WHERE GoodId IN ( SELECT cart.`GoodId` FROM cart WHERE UserId= " + UserId + " )";
//        System.out.println("sql:" + sql);
//        ResultSet resultSet = DBUtils.getResultSet(sql);
//        try {
//            while (resultSet.next()) {
//                mGoodsList.add(new Goods(
//                        resultSet.getInt("GoodId"),
//                        resultSet.getString("GoodPic"),
//                        resultSet.getDouble("GoodPrice"),
//                        resultSet.getInt("GoodNumber"),
//                        resultSet.getString("GoodName"),
//                        resultSet.getString("GoodDescribe"),
//                        resultSet.getInt("StoreId")));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return mGoodsList;
//        } finally {
//            DBUtils.closeConnection();
//        }

        List<Goods> mGoodsList = getmIGoodsDao().selectByUserId(Integer.parseInt(UserId));

        return mGoodsList;
    }


    public static List<Integer> getStoreIdsByUserId(String UserId) {
//        List<Integer> mStoreList = new ArrayList<>();
//        String sql = "SELECT goods.`StoreId` FROM goods WHERE GoodId IN ( SELECT cart.`GoodId` FROM cart WHERE UserId= " + UserId + " ) GROUP BY goods.`StoreId`";
//        ResultSet resultSet = DBUtils.getResultSet(sql);
//        try {
//            while (resultSet.next()) {
//                mStoreList.add(resultSet.getInt("StoreId"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return mStoreList;
//        } finally {
//            DBUtils.closeConnection();
//        }
        List<Integer> mStoreList = getmIGoodsDao().selectStoreIdByUserId(Integer.parseInt(UserId));

        return mStoreList;
    }


    public static void main(String[] args) {
        List<Goods> goodListByUserId = getGoodListByUserId("1");
        List<Integer> storeListByUserId = getStoreIdsByUserId("1");
        if (storeListByUserId != null && storeListByUserId.size() != 0) {
            for (int i = 0; i < storeListByUserId.size(); i++) {
                System.out.println(i + ":" + new Gson().toJson(storeListByUserId.get(i)));
            }
        }
    }
}
