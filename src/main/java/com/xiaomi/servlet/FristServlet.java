package com.xiaomi.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 写Servlet的时候要在web.xml文件中注册一下，两个标签
 *
 * 每一个servlet都要配置两个  一个servlet标签  一个servlet-mapping标签
 * 中servlet标签下的name和servlet-mapping标签的name是一样的
 * servlet标签下的servlet-class是servlet所在的目录
 * servlet-mapping标签下的url-pattern是在jsp中写的超链接的路径
 *
 *
 * 执行流程
 *
 * 首先用户在网页上点了一个超链接，比如：servlet/FristServlet
 * 然后会去web.xml文件中查找servlet-mapping标签下url-pattern是servlet/FristServlet的Servlet的name
 *
 * 然后在根据name去servlet标签 下的name类，然后去根据servlet-class标签下的路径去找对应的类文件
 *
 * 然后再根据请求方式去执行get方法或者post方法
 *
 *
 * 生命周期：
 *
 * 首先是构造方法
 * 然后
 * 1、初始化阶段：调用init()方法
 *
 * 2、调用doget()/dopost()
 *
 * 3、终止的时候调用 destory()方法
 *
 *
 * 在下列时刻Servlet容器装在Servlet:
 *
 * 1、Servlet容器启动时自动装载一些Servlet，需要在web.xml文件中<Servlet></Servlet>标签之前添加如下标签：
 * <loadon-startup>1</loadon-startup>,标签中的数字越小，优先级越高。
 *
 * 2、在Servlet容器启动后，客户端首次像Servlet发送请求的时候
 *
 * 3、Servlet被重新修改之后
 *
 * Servlet是常驻内存的，并且只会初始化一次，init方法只会被调用一次
 *
 *
 *
 */
@WebServlet(name = "FristServlet")
public class FristServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("处理post请求...");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charest=utf-8");
        out.println("<H1>helloServetl!Post</H1>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("处理get请求...");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charest=utf-8");
        out.println("<H1>helloServetl!Get</H1>");
    }
}
