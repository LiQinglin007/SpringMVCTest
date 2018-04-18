package com.xiaomi.dao;

import com.google.gson.Gson;
import com.xiaomi.bean.UserBean;
import com.xiaomi.db.DBtools;
import com.xiaomi.utils.DBUtils;
import com.xiaomi.utils.PageBean;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserBeanDao {

    /**
     * 分页查询用户  原生的idbc写法
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


    /**
     * 使用框架查询全部
     *
     * @return
     */
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
            if (sqlSession != null) sqlSession.close();
        }
        return mList;
    }

    /**
     * 使用框架查询
     *
     * @param page
     * @param size
     * @return
     */
    public static List<UserBean> getUserListByMyBatis(int page, int size) {
        List<UserBean> mList = new ArrayList<>();
        SqlSession sqlSession = null;
        try {
            sqlSession = DBtools.getSqlSession();
            mList = sqlSession.selectList("User.selectByPage", new PageBean((page - 1) * size, size));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return mList;
    }


    public static List<UserBean> getUserByName(String userName) {
        List<UserBean> mList = new ArrayList<>();
        SqlSession sqlSession = null;
        try {
            sqlSession = DBtools.getSqlSession();
            mList = sqlSession.selectList("User.selectByName", userName);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return mList;
    }


    /**
     * 按用户名删除
     *
     * @param userId
     */
    public static void deleteById(int userId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBtools.getSqlSession();
            sqlSession.selectList("User.deleteById", userId);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    /**
     * 修改数据
     *
     * @param userId
     * @param UserName
     */
    public static void updateUserNameById(int userId, String UserName) {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBtools.getSqlSession();
            sqlSession.selectList("User.updateById", new UserBean(userId, UserName));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    public static void main(String[] args) {
//        final Logger LOGGER = Logger.getLogger(String.valueOf(UserBeanDao.class));
//        LOGGER.warning("aaaaaaaaaa");
//
//        // 记录debug级别的信息
//        // 记录info级别的信息
//        LOGGER.info("This is info message.");
//        // 记录warn级别的信息
//        LOGGER.info("This is warn message.");
//        // 记录error级别的信息

//        ArrayList<UserBean> userList = UserBeanDao.getUserList(1, 30);
//      ===================================  下边是框架的用法==============
        //删除一条
//        deleteById(8);
        updateUserNameById(30, "小米用户7");
        //查询全部用户
        List<UserBean> userList = getUserList();
        //分组查询
//        List<UserBean> userList = getUserListByMyBatis(2, 5);
        //条件查询
//        List<UserBean> userList = getUserByName("小米用户7");
        if (userList != null && userList.size() != 0) {
            String s = new Gson().toJson(userList);
            System.out.println("查询来的数据.size:" + userList.size() + "内容：" + s);
        } else {
            System.out.println("数据为空");
        }
    }
}
