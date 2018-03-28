package com.xiaomi.bean;

public class Store {
    private int StoreId;
    private String StoreName;


    @Override
    public String toString() {
        return "Store{" +
                "StoreId=" + StoreId +
                ", StoreName='" + StoreName + '\'' +
                '}';
    }

    public Store() {
    }

    public Store(int storeId, String storeName) {
        StoreId = storeId;
        StoreName = storeName;
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
}
