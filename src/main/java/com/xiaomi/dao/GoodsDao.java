package com.xiaomi.dao;


import com.xiaomi.bean.Goods;
import com.xiaomi.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GoodsDao {
    /**
     * 通过用户id和店铺id查询购物车里边该商店的全部商品
     *
     * @return
     */
    public static ArrayList<Goods> getGoodListByUserId(String UserId) {
        ArrayList<Goods> mGoodsList = new ArrayList<>();
        String sql = " SELECT * FROM goods WHERE GoodId IN ( SELECT cart.`GoodId` FROM cart WHERE UserId= " + UserId + " )";
        System.out.println("sql:" + sql);
        ResultSet resultSet = DBUtils.getResultSet(sql);
        try {
            while (resultSet.next()) {
                mGoodsList.add(new Goods(
                        resultSet.getInt("GoodId"),
                        resultSet.getString("GoodPic"),
                        resultSet.getDouble("GoodPrice"),
                        resultSet.getInt("GoodNumber"),
                        resultSet.getString("GoodName"),
                        resultSet.getString("GoodDescribe"),
                        resultSet.getInt("StoreId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return mGoodsList;
        } finally {
            DBUtils.closeConnection();
        }
        return mGoodsList;
    }


    public static ArrayList<Integer> getStoreIdsByUserId(String UserId) {
        String sql = "SELECT goods.`StoreId` FROM goods WHERE GoodId IN ( SELECT cart.`GoodId` FROM cart WHERE UserId= " + UserId + " ) GROUP BY goods.`StoreId`";
        ArrayList<Integer> mStoreList = new ArrayList<>();
        ResultSet resultSet = DBUtils.getResultSet(sql);
        try {
            while (resultSet.next()) {
                mStoreList.add(resultSet.getInt("StoreId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return mStoreList;
        } finally {
            DBUtils.closeConnection();
        }
        return mStoreList;
    }
}
