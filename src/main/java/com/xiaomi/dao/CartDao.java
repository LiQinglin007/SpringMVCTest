package com.xiaomi.dao;



import com.xiaomi.bean.CartBean;
import com.xiaomi.utils.DBUtils;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CartDao {

    public static ArrayList<CartBean> getCartByUserId(int UserId) {
        String sql = "select * from cart where UserId = " + UserId ;
        ResultSet resultSet = DBUtils.getResultSet(sql);
        ArrayList<CartBean> mCartList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                CartBean mCartBean = new CartBean(
                        resultSet.getInt("CartId"),
                        resultSet.getInt("UserId"),
                        resultSet.getInt("Number"),
                        resultSet.getInt("GoodId"),
                        resultSet.getInt("StoreId")
                );
                mCartList.add(mCartBean);
            }
            return mCartList;
        } catch (Exception e) {
            e.printStackTrace();
            return mCartList;
        } finally {
            DBUtils.closeConnection();
        }
    }

    public static int getGoodNumberByGoodId(int GoodId) {
        String sql = "select Number from cart where GoodId = " + GoodId ;
        ResultSet resultSet = DBUtils.getResultSet(sql);
        int number=-1;
        try {
            while (resultSet.next()) {
                 number = resultSet.getInt("Number");
            }
            return number;
        } catch (Exception e) {
            e.printStackTrace();
            return number;
        } finally {
            DBUtils.closeConnection();
        }
    }

    public static void main(String[] args) {
        ArrayList<CartBean> cartByUserId = CartDao.getCartByUserId(1);
        for (CartBean cartBean : cartByUserId) {
            System.out.println(cartBean.toString());
        }
    }
}
