package com.xiaomi.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DBtools {
//    SqlSession
// 1\向sql语句传送参数
//    2\执行sql语句的能力
//    3\获取sql结果的能力
//    4\事务的控制

    //创建能执行映射文件中sql的sqlSession
    public static SqlSession getSqlSession() {
        SqlSessionFactory sessionFactory = null;
        //使用MyBatis提供的Resources类加载mybatis的配置文件
        Reader reader = null;
        SqlSession sqlSession = null;
        try {
            reader = Resources.getResourceAsReader("Configuration.xml");
            //构建sqlSession的工厂
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = sessionFactory.openSession();
        } catch (IOException e) {
            System.out.println("没连接上" + e.toString());
            e.printStackTrace();
        }
        return sqlSession;
    }


    public static void main(String[] args) {
        SqlSession sqlSession = getSqlSession();
        if (sqlSession != null) {
            sqlSession.close();
            System.out.println("已经连接上了");
        } else {
            System.out.println("没连接上");
        }
    }
}
