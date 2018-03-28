package com.xiaomi.dao;



import com.xiaomi.bean.Goods;
import com.xiaomi.utils.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodsDao {
    public static Goods getAllGoodsByGoodId(int goodId) {
        String sql = "select * from goods where GoodId =" + goodId;
        ResultSet resultSet = DBUtils.getResultSet(sql);
        Goods mGood = null;
        try {
            while (resultSet.next()) {
                mGood = new Goods(
                        resultSet.getInt("GoodId"),
                        resultSet.getString("GoodPic"),
                        resultSet.getDouble("GoodPrice"),
                        resultSet.getInt("GoodNumber"),
                        resultSet.getString("GoodName"),
                        resultSet.getString("GoodDescribe"),
                        resultSet.getInt("StoreId")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return mGood;
        } finally {
            DBUtils.closeConnection();
        }
        return mGood;
    }


    public static void main(String[] args) {
        Goods allGoodsByGoodId = GoodsDao.getAllGoodsByGoodId(1);
        System.out.println(allGoodsByGoodId.toString());
    }


}
