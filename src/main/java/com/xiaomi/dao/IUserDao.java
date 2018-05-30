package com.xiaomi.dao;

import com.xiaomi.bean.UserBean;
import com.xiaomi.utils.PageBean;

import java.util.List;

public interface IUserDao {
    List<UserBean> selectAll();

    List<UserBean> selectByName(String userName);

    List<UserBean> selectByNameList(List<String> userNameList);

    int deleteById(int userId);

    int deleteByNameList(List<String> userNameList);

    int updateById(UserBean userBean);

    List<UserBean> selectByPage(PageBean pageBean);
}
