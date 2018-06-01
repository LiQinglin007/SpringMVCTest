package com.xiaomi.interceptor;

import com.xiaomi.utils.PageBean;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;

/**
 * 分页拦截器  MyBatis
 * <p>
 * 这里 规定整个项目中所有的需要使用分页拦截器的id(xml文件中的id)   都必须由ByPageInterceptor来结尾
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //然后被拦截下来的才会走这个方法
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//        MetaObject metaObject=MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement value = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        //拿到配置文件中的sql语句的id
        String id = value.getId();
        if (id.matches(".+ByPageInterceptor$")) {//以ByPageInterceptor来结尾的
            //原始的sql语句
            String sql = statementHandler.getBoundSql().getSql();
            //拿到参数
            PageBean pageBean = (PageBean) statementHandler.getBoundSql().getParameterObject();
            //改造后的sql语句
            String pageSql = sql + " limit " + pageBean.getStartIndex() + " , " + pageBean.getPageSize();
            //修改修改后的sql
            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }
        return invocation.proceed() ;
    }

    @Override
    public Object plugin(Object target) {//先走这个方法个
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
