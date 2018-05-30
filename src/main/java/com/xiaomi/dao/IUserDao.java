package com.xiaomi.dao;

import com.xiaomi.bean.UserBean;
import com.xiaomi.utils.PageBean;

import java.util.List;

public interface IUserDao {
    List<UserBean> selectAll();

    List<UserBean> selectByName(String userName);

    List<UserBean> selectByNameList(List<String> userNameList);

    void deleteById(int userId);

    void deleteByNameList(List<String> userNameList);

    void updateById(UserBean userBean);

    List<UserBean> selectByPage(PageBean pageBean);
}
