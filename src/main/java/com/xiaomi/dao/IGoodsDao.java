package com.xiaomi.dao;

import com.xiaomi.bean.Goods;

import java.util.List;

public interface IGoodsDao {
    List<Goods> selectByUserId(int userId);
    List<Integer> selectStoreIdByUserId(int userId);
}
