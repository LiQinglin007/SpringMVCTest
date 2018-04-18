package com.xiaomi.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mysql.jdbc.log.LogUtils;
import com.xiaomi.bean.*;
import com.xiaomi.dao.CartDao;
import com.xiaomi.dao.GoodsDao;
import com.xiaomi.dao.StoreDao;
import com.xiaomi.dao.UserBeanDao;
import com.xiaomi.utils.CheckStringEmptyUtils;


import com.xiaomi.utils.JSONUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.WeakHashMap;


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
        JsonArray jsonArray = new JsonArray();
        String msg = "成功";
        int errorcode = 200;
        if (CheckStringEmptyUtils.IsEmpty(userid)) {
            msg = "userid是空的";
            errorcode = -100;
            System.out.println("userid是空的");
        } else {
            System.out.println("userid:" + userid);
            //先去查询所有的购物车
            ArrayList<CartBean> cartByUserId = CartDao.getCartByUserId(1);
            ArrayList<Goods> mGoodsList = new ArrayList<>();
            ArrayList<Store> mStoreList = new ArrayList<>();//店铺的列表
            ArrayList<Integer> mStoreIdList = new ArrayList<>();
            if (cartByUserId != null) {
                //然后根据购物车的店铺id去查询店铺
                for (CartBean cartBean : cartByUserId) {
                    if (!mStoreIdList.contains(cartBean.getStoreId())) {
                        Store storeListByStoreId = StoreDao.getStoreListByStoreId(cartBean.getStoreId());
                        if (storeListByStoreId != null) {
                            mStoreList.add(storeListByStoreId);
                            mStoreIdList.add(cartBean.getStoreId());
                        }
                    }
                    mGoodsList.add(GoodsDao.getAllGoodsByGoodId(cartBean.getGoodId()));
                }
            }

            ArrayList<AppStore> mAppStoreList = new ArrayList<>();
            //遍历所有的商铺
            for (Store store : mStoreList) {
                AppStore mAppStore = new AppStore();
                mAppStore.setStoreId(store.getStoreId());
                mAppStore.setStoreName(store.getStoreName());
                ArrayList<Goods> mAppGoodList = new ArrayList<>();
                //然后去查找商铺里的商品
                for (int i = 0; i < mGoodsList.size(); ) {
                    if (mGoodsList.get(i).getStoreId() == store.getStoreId()) {
                        mGoodsList.get(i).setGoodNumber(CartDao.getGoodNumberByGoodId(mGoodsList.get(i).getGoodId()));
                        mAppGoodList.add(mGoodsList.get(i));
                        mGoodsList.remove(mGoodsList.get(i));
                    } else {
                        i++;
                    }
                }
                mAppStore.setmGoodList(mAppGoodList);
                mAppStoreList.add(mAppStore);
            }
            jsonArray = JSONUtils.getJSONArrayByList(mAppStoreList);
        }
        finalData(response, msg, errorcode, jsonArray);
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
        ArrayList<UserBean> userList = UserBeanDao.getUserList(page, size);
        JsonArray jsonArray = JSONUtils.getJSONArrayByList(userList);
        String msg = "成功";
        int errorcode = 200;
        return finalData(response, msg, errorcode, jsonArray);
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
        String realPath = "";
        String msg = "成功";
        int errorcode = 200;
        if (file == null || file.isEmpty()) {
            msg = "文件不能为空";
            errorcode = -100;
        } else {
            String RootPath = session.getServletContext().getRealPath("/");
            realPath = "static/" + System.currentTimeMillis() + ".jpg";
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(RootPath + realPath));
        }
        return finalData(response, msg, errorcode, realPath);
    }

    @ResponseBody
    @RequestMapping("/uploadFiles")
    public String uploadFiles(HttpServletResponse response, HttpSession session, @RequestParam(value = "file") MultipartFile[] files) throws IOException {
        System.out.println("批量上传");
        String realPath = "";
        String msg = "成功";
        int errorcode = 200;
        if (files == null || files.length == 0) {
            msg = "文件不能为空";
            errorcode = -100;
        } else {
            String RootPath = session.getServletContext().getRealPath("/");
            for (MultipartFile file : files) {
                String savePath = "static/" + System.currentTimeMillis() + ".jpg";
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(RootPath + savePath));
                realPath += savePath + ",";
            }
        }
        realPath = realPath.length() > 0 ? realPath.substring(0, realPath.length() - 1) : realPath;
        return finalData(response, msg, errorcode, realPath);
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
     * @param msg
     * @param errorcode
     * @param data
     * @return
     */
    private String finalData(HttpServletResponse response, String msg, int errorcode, Object data) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            errorcode = -100;
            data = null;
            msg = e.toString();
            e.printStackTrace();
        }
        AppBean mBean = new AppBean(msg, errorcode, data);
        Gson gson = new Gson();
        writer.print(gson.toJson(mBean));
        writer.flush();
        writer.close();

        return gson.toJson(mBean);
    }




    /**
     * 这里写一点Gson的用法
     *
     * @param args
     */
    public static void main(String[] args) {
//        /**
//         * 把Bean转换成json
//         */
//        /**
//         * 这里如果想把某个属性，在转换成jSON的时候换个名字，去对应的字段上边加上SerializedName注解，详情去AppBean里边看
//         * 如果有没些属性，在转换成JSON的时候，不想暴露出来，就去使用transient关键字
//         */
//        AppBean mBean = new AppBean("失败", -100, "");
//        Gson gson = new Gson();
//        String BeanToJson = gson.toJson(mBean);
//        System.out.println("BeanToGson:" + BeanToJson);
//
//        /**
//         * 再把JSON转换成Bean
//         */
//        Gson gson1 = new Gson();
//        AppBean BeanFromJson = gson1.fromJson(BeanToJson, AppBean.class);
//        System.out.println("BeanFromJson:" + BeanFromJson.toString());

    }


}
