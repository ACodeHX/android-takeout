package com.aa.meituan;

public class StoreMinute {
    private String storeName;
    private String storePrice;
    private int storeImage;
    private String storeEvaluate;


    public StoreMinute(String StoreName, String StorePrice, int storeImage, String storeEvaluate) {
        this.storeName = StoreName;
        this.storePrice = StorePrice;
        this.storeImage = storeImage;
        this.storeEvaluate = storeEvaluate;
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

    public String getStoreEvaluate() { return  storeEvaluate; }
}