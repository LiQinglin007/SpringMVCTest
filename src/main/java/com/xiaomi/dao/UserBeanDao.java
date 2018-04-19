package com.xiaomi.dao;

import com.google.gson.Gson;
import com.xiaomi.bean.UserBean;
import com.xiaomi.db.DBtools;
import com.xiaomi.utils.DBUtils;
import com.xiaomi.utils.PageBean;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UserBeanDao {
    static SqlSession sqlSession = null;

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
     * 使用框架  分页查询
     *
     * @param page
     * @param size
     * @return
     */
    public static List<UserBean> getUserListByMyBatis(int page, int size) {
        List<UserBean> mList = new ArrayList<>();
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

    /**
     * 按用户名称查询
     *
     * @param userName
     * @return
     */
    public static List<UserBean> getUserByName(String userName) {
        List<UserBean> mList = new ArrayList<>();
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
     * 按用户名称查询  批量查询
     *
     * @param userNameList
     * @return
     */
    public static List<UserBean> selectByList(List<String> userNameList) {
        List<UserBean> mList = new ArrayList<>();
        try {
            sqlSession = DBtools.getSqlSession();
            mList = sqlSession.selectList("User.selectByNameList", userNameList);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("报错了：" + e.toString());
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
        return mList;
    }


//    这里注意增删改要手动提交事务
//     sqlSession.commit();

    /**
     * 按用户id删除
     *
     * @param userId
     */
    public static void deleteById(int userId) {
        try {
            sqlSession = DBtools.getSqlSession();
            sqlSession.selectList("User.deleteById", userId);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }

    /**
     * 批量删除
     *
     * @param mNameList
     */
    public static void deleteByNameList(List<String> mNameList) {
        try {
            sqlSession = DBtools.getSqlSession();
            sqlSession.delete("User.deleteByNameList", mNameList);
            sqlSession.commit();
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
        try {
            sqlSession = DBtools.getSqlSession();
            sqlSession.update("User.updateById", new UserBean(userId, UserName));
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) sqlSession.close();
        }
    }


    final static Logger LOGGER = Logger.getLogger(String.valueOf(UserBeanDao.class));

    public static void main(String[] args) {
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");
//        ArrayList<UserBean> userList = UserBeanDao.getUserList(1, 30);
//      ===================================  下边是框架的用法==============
        //删除一条
        deleteById(11);
//        updateUserNameById(30, "小米用户7");

        //按名称批量删除
//        List<String> mListName = new ArrayList<>();
//        mListName.add("小米用户7");
//        mListName.add("小米用户8");
//        mListName.add("小米用户10");
//        deleteByNameList(mListName);
//        List<UserBean> userList = selectByList(mListName);

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
