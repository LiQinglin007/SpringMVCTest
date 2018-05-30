package com.xiaomi.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.xiaomi.bean.*;
import com.xiaomi.dao.*;
import com.xiaomi.utils.CheckStringEmptyUtils;


import com.xiaomi.utils.JSONUtils;
import org.apache.commons.io.FileUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class MainController {
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("123");
        return "index";
    }

    @RequestMapping("/hello1")
    public String hello1(String Userid) {
        System.out.println("456userid:" + Userid);
        return "index";
    }

    @RequestMapping("/login")
    public void Login(HttpServletResponse response, String phone, String password) {
        response.setHeader("Content-type", "textml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");


    }

    /**
     * 购物车
     *
     * @param response
     * @param userid
     */
    @RequestMapping("/getShop")
    public void getShop(HttpServletResponse response, String userid) {
        response.setHeader("Content-type", "textml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        AppBean mAppBean = new AppBean();
        if (CheckStringEmptyUtils.IsEmpty(userid)) {
            mAppBean.setMsg("userid是空的");
            mAppBean.setCode(-100);
        } else {
            List<Goods> goodListByUserId = GoodsDao.getGoodListByUserId(userid);
            List<Integer> storeIdsByUserId = GoodsDao.getStoreIdsByUserId(userid);
            ArrayList<AppStore> mStoreList = new ArrayList<>();
            for (int i = 0; i < storeIdsByUserId.size(); i++) {
                AppStore storeListByStoreId = StoreDao.getStoreListByStoreId(storeIdsByUserId.get(i));
                int size = goodListByUserId.size();
                for (int i1 = 0; i1 < size; i1++) {
                    if (goodListByUserId.get(i1).getStoreId() == storeIdsByUserId.get(i)) {
                        storeListByStoreId.addGoods(goodListByUserId.get(i1));
                        goodListByUserId.remove(i1);
                        i1 = i1 - 1;
                        size = goodListByUserId.size();
                    }
                }
                mStoreList.add(storeListByStoreId);
            }
            mAppBean.setData(JSONUtils.getJSONArrayByList(mStoreList));
        }
        finalData(response, mAppBean);
    }


    @RequestMapping("/getShopByMyBatis")
    public void getShopByMyBatis(HttpServletResponse response, String userid) {
        setResponseEncoding(response);
        AppBean mAppBean = new AppBean();
        if (CheckStringEmptyUtils.IsEmpty(userid)) {
            mAppBean.setMsg("userid是空的");
            mAppBean.setCode(-100);
        } else {
            List<AppStore> appStoreByUserId = AppStoreDao.getAppStoreByUserId(userid);
            mAppBean.setData(JSONUtils.getJSONArrayByList(appStoreByUserId));
        }
        finalData(response, mAppBean);
    }


    /**
     * 分页数据
     *
     * @param response
     * @param page
     * @param size
     * @ResponseBody 该注解用于将Controller的方法返回的对象，根据HTTP Request Header的Accept的内容,通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。
     * 使用时机：
     * 返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用.
     * 配置返回JSON和XML数据
     */
    @ResponseBody
    @RequestMapping("/getDataList")
    public String getDataList(HttpServletResponse response, int page, int size) {
        List<UserBean> userList = UserBeanDao.getUserListByMyBatis(page, size);
        AppBean mAppBean = new AppBean();
        mAppBean.setData(JSONUtils.getJSONArrayByList(userList));
        return finalData(response, mAppBean);
    }


    /**
     * 文件的上传
     *
     * @param response
     * @param session
     * @param file
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/uploadFile")
    public String uploadFile(HttpServletResponse response, HttpSession session, @RequestParam(value = "file") MultipartFile file) throws IOException {
        AppBean mAppBean = new AppBean();
        if (file == null || file.isEmpty()) {
            mAppBean.setMsg("文件不能为空");
            mAppBean.setCode(-100);
        } else {
            String RootPath = session.getServletContext().getRealPath("/");
            String realPath = "static/" + System.currentTimeMillis() + ".jpg";
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(RootPath + realPath));
            mAppBean.setData(realPath);
        }
        return finalData(response, mAppBean);
    }

    @ResponseBody
    @RequestMapping("/uploadFiles")
    public String uploadFiles(HttpServletResponse response, HttpSession session, @RequestParam(value = "file") MultipartFile[] files) throws IOException {
        System.out.println("批量上传");

        AppBean mAppBean = new AppBean();
        if (files == null || files.length == 0) {
            mAppBean.setMsg("文件不能为空");
            mAppBean.setCode(-100);
        } else {
            String RootPath = session.getServletContext().getRealPath("/");
            String realPath = "";
            for (MultipartFile file : files) {
                String savePath = "static/" + System.currentTimeMillis() + ".jpg";
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(RootPath + savePath));
                realPath += savePath + ",";
            }
            realPath = realPath.length() > 0 ? realPath.substring(0, realPath.length() - 1) : realPath;
            mAppBean.setData(realPath);
        }
        return finalData(response, mAppBean);
    }

    /**
     * 开始设置编码（貌似不用了，难道是配置文件）
     *
     * @param response
     */
    private void setResponseEncoding(HttpServletResponse response) {
        response.setHeader("Content-type", "textml;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
    }

    /**
     * 设置最后的返回值
     *
     * @param response
     * @return
     */
    private String finalData(HttpServletResponse response, AppBean appBean) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            appBean.setCode(-100);
            appBean.setData(null);
            appBean.setMsg(e.toString());
            e.printStackTrace();
        }
        Gson gson = new Gson();
        writer.print(gson.toJson(appBean));
        writer.flush();
        writer.close();

        return gson.toJson(appBean);
    }


    /**
     * 这里写一点Gson的用法
     *
     * @param args
     */
    public static void main(String[] args) {

    }

}
