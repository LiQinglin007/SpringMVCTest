package com.xiaomi.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class DBtools {
//    SqlSession
// 1\向sql语句传送参数
//    2\执行sql语句的能力
//    3\获取sql结果的能力
//    4\事务的控制

    private final static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "Configuration.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            System.out.println(e.getMessage()+"aaa");
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }




    public static void main(String[] args) {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
    }
}
