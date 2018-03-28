package com.xiaomi.bean;

/**
 * 商品类
 */
public class Goods {
    private int GoodId;
    private String GoodPic;
    private double GoodPrice;
    private int GoodNumber;
    private String GoodsName;
    private String GoodsDescribe;
    private int StoreId;

    public Goods() {
    }

    public Goods(int goodId, String goodPic, double goodPrice, int goodNumber, String goodsName, String goodsDescribe, int storeId) {
        GoodId = goodId;
        GoodPic = goodPic;
        GoodPrice = goodPrice;
        GoodNumber = goodNumber;
        GoodsName = goodsName;
        GoodsDescribe = goodsDescribe;
        StoreId = storeId;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "GoodId=" + GoodId +
                ", GoodPic='" + GoodPic + '\'' +
                ", GoodPrice=" + GoodPrice +
                ", GoodNumber=" + GoodNumber +
                ", GoodsName='" + GoodsName + '\'' +
                ", GoodsDescribe='" + GoodsDescribe + '\'' +
                ", StoreId=" + StoreId +
                '}';
    }

    public int getGoodId() {
        return GoodId;
    }

    public void setGoodId(int goodId) {
        GoodId = goodId;
    }

    public String getGoodPic() {
        return GoodPic;
    }

    public void setGoodPic(String goodPic) {
        GoodPic = goodPic;
    }

    public double getGoodPrice() {
        return GoodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        GoodPrice = goodPrice;
    }

    public int getGoodNumber() {
        return GoodNumber;
    }

    public void setGoodNumber(int goodNumber) {
        GoodNumber = goodNumber;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public String getGoodsDescribe() {
        return GoodsDescribe;
    }

    public void setGoodsDescribe(String goodsDescribe) {
        GoodsDescribe = goodsDescribe;
    }

    public int getStoreId() {
        return StoreId;
    }

    public void setStoreId(int storeId) {
        StoreId = storeId;
    }
}
