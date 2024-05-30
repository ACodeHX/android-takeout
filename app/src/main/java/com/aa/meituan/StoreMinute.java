package com.aa.meituan;

public class StoreMinute {
    private String storeName;
    private String storePrice;
    private int storeImage;
    private String storeEvaluate;
    private String deliveryTime;
    private Class<?> targetActivity;    //指示点击该条目时应跳转的目标活动


    public StoreMinute(String StoreName, String StorePrice, int storeImage, String storeEvaluate, String deliveryTime,Class<?> targetActivity) {
        this.storeName = StoreName;
        this.storePrice = StorePrice;
        this.storeImage = storeImage;
        this.storeEvaluate = storeEvaluate;
        this.deliveryTime = deliveryTime;
        this.targetActivity = targetActivity;
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

    public Class<?> getTargetActivity() {
        return targetActivity;
    }
}