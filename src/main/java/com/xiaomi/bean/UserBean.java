package com.xiaomi.bean;

public class UserBean {

    private int userId;
    private String userName;

    public UserBean(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserBean() {
    }


    @Override
    public String toString() {
        return "UserBean{" +
                "userId=" + userId +
                ", userName=" + userName +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
