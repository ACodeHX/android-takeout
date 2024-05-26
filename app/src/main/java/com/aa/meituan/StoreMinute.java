package com.aa.meituan;

public class StoreMinute {
    private String storeName;
    private String storePrice;
    private int storeImage;
    private String storeEvaluate;
    private String deliveryTime;


    public StoreMinute(String StoreName, String StorePrice, int storeImage, String storeEvaluate, String deliveryTime) {
        this.storeName = StoreName;
        this.storePrice = StorePrice;
        this.storeImage = storeImage;
        this.storeEvaluate = storeEvaluate;
        this.deliveryTime = deliveryTime;
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

    public String getDeliveryTime() {
        return deliveryTime;
    }
}