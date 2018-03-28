package com.xiaomi.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DBtools {

    //创建能执行映射文件中sql的sqlSession
    public static SqlSession getSqlSession() throws IOException {
        SqlSessionFactory sessionFactory = null;
        //使用MyBatis提供的Resources类加载mybatis的配置文件
        Reader reader = Resources.getResourceAsReader("Configuration.xml");
        //构建sqlSession的工厂
        sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sessionFactory.openSession();
        return sqlSession;
    }


    public static void main(String[] args) {
        try {
            SqlSession sqlSession = getSqlSession();
            if(sqlSession!=null){
                sqlSession.close();
                System.out.println("已经连接上了");
            }else{
                System.out.println("没连接上");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("没连接上"+e.toString());
        }
    }
}
