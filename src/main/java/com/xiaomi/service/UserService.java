package com.xiaomi.service;

import com.xiaomi.dao.UserBeanDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {



    @Override
    public List<UserBeanDao> getUser() {
        return null;
    }
}
