package com.aa.meituan;

public class StoreMinute {
    private String storeName;
    private String storePrice;
    private int storeImage;


    public StoreMinute(String StoreName, String StorePrice, int storeImage) {
        this.storeName = StoreName;
        this.storePrice = StorePrice;
        this.storeImage = storeImage;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getSubtitle() {
        return storePrice;
    }

    public int getStoreImage() {
        return storeImage;
    }
}