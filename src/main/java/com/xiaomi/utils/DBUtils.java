package com.xiaomi.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtils {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    //todo  这里有问题，如果上一个链接没有结束，下一个链接就进来了，
    // todo 这时候上一个的资源还没有释放，返回回去的就是上一个的了。
    public static ResultSet getResultSet(String sql) {
        if(connection==null){
            try {
                connection = DBHelper.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement == null) {
            try {
                //执行sql
                preparedStatement = connection.prepareStatement(sql);
                //拿到返回结果
                resultSet = preparedStatement.executeQuery();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            closeConnection();
            getResultSet(sql);
        }
        return resultSet;
    }


    public static boolean closeConnection() {
        boolean flag = false;
        // 释放数据集对象
        if (resultSet != null) {
            try {
                resultSet.close();
                resultSet = null;
                // 释放语句对象
                if (preparedStatement != null) {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                flag = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }

}
