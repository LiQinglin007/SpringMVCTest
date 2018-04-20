package com.xiaomi.bean;

import com.google.gson.annotations.SerializedName;

public class AppBean {
    private String msg = "成功";
    private int code = 200;
    @SerializedName("Data")
    private Object data = null;
    private transient String message;//不想暴露给json的

    public AppBean(String msg, int code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public AppBean() {
        msg = "成功";
        code = 200;
        data = null;
    }

    @Override
    public String toString() {
        return "AppBean{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
