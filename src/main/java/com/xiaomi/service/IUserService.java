package com.xiaomi.service;

import com.xiaomi.dao.UserBeanDao;

import java.util.List;

public interface IUserService {
    List<UserBeanDao> getUser();
}
