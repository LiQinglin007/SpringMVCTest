package com.xiaomi.dao;

import com.google.gson.Gson;
import com.xiaomi.bean.UserBean;
import com.xiaomi.db.DBtools;
import com.xiaomi.utils.DBUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserBeanDao {


    public static List<UserBean> getUserList() {
        List<UserBean> mList = new ArrayList<>();
        SqlSession sqlSession = null;
        try {
            //拿到数据库连接
            sqlSession = DBtools.getSqlSession();
            //这里就拿到结果了
            mList = sqlSession.selectList("User.selectAll");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return mList;
    }


    /**
     * 分页查询用户
     *
     * @param page
     * @param size
     * @return
     */
    public static ArrayList<UserBean> getUserList(int page, int size) {
        ArrayList<UserBean> mList = new ArrayList<>();
        String sql = "select * from user limit " + (page - 1) * size + "," + size;
        ResultSet resultSet = DBUtils.getResultSet(sql);
        //然后去循环一个一个解析
        try {
            while (resultSet.next()) {
                UserBean mUserBean = new UserBean(resultSet.getInt("UserId"),
                        resultSet.getString("UserName")
                );
                mList.add(mUserBean);
            }
            return mList;
        } catch (Exception e) {
            e.printStackTrace();
            return mList;
        } finally {
            DBUtils.closeConnection();
        }
    }


    public static void main(String[] args) {
//        ArrayList<UserBean> userList = UserBeanDao.getUserList(1, 30);
        List<UserBean> userList = getUserList();
        if (userList != null && userList.size() != 0) {
            String s = new Gson().toJson(userList);
            System.out.println("查询来的数据.size:" + userList.size() + "内容：" + s);
        } else {
            System.out.println("数据为空");
        }
    }
}
