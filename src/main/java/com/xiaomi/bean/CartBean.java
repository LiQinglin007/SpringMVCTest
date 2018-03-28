package com.xiaomi.bean;

/**
 * 购物车类
 */
public class CartBean {
    private int CartId;
    private int UserId;
    private int Number;
    private int GoodId;
    private int StoreId;

    @Override
    public String toString() {
        return "CartBean{" +
                "CartId=" + CartId +
                ", UserId=" + UserId +
                ", Number=" + Number +
                ", GoodId=" + GoodId +
                ", StoreId=" + StoreId +
                '}';
    }


    public CartBean() {
    }

    public CartBean(int cartId, int userId, int number, int goodId,int storeId) {
        CartId = cartId;
        UserId = userId;
        Number = number;
        GoodId = goodId;
        StoreId = storeId;
    }


    public int getStoreId() {
        return StoreId;
    }

    public void setStoreId(int storeId) {
        StoreId = storeId;
    }

    public int getCartId() {
        return CartId;
    }

    public void setCartId(int cartId) {
        CartId = cartId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public int getGoodId() {
        return GoodId;
    }

    public void setGoodId(int goodId) {
        GoodId = goodId;
    }
}
