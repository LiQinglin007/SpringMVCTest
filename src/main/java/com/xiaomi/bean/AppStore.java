package com.xiaomi.bean;

import java.util.ArrayList;

public class AppStore {
    private int StoreId;
    private String StoreName;
    private ArrayList<Goods> mGoodList=new ArrayList<>();

    public void addGoods(Goods mGood) {
        mGoodList.add(mGood);
    }

    public AppStore() {
    }

    public int getStoreId() {
        return StoreId;
    }

    public void setStoreId(int storeId) {
        StoreId = storeId;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public ArrayList<Goods> getmGoodList() {
        return mGoodList;
    }

    public void setmGoodList(ArrayList<Goods> mGoodList) {
        this.mGoodList.clear();
        this.mGoodList.addAll(mGoodList);
    }

    @Override
    public String toString() {
        return "AppStore{" +
                "StoreId=" + StoreId +
                ", StoreName='" + StoreName + '\'' +
                ", mGoodList=" + mGoodList +
                '}';
    }


}
