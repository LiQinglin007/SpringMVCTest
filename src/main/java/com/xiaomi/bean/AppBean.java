package com.xiaomi.bean;

import com.google.gson.annotations.SerializedName;

public class AppBean {
    private String msg;
    private int code;
    @SerializedName("Data")
    private Object data;
    private transient String message;//不想暴露给json的

    public AppBean(String msg, int code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
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
