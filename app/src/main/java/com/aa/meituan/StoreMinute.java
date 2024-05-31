package com.aa.meituan;

public class StoreMinute {
    private int storeID;
    private String storeName;
    private String storePrice;
    private String storeImage;
    private String storeEvaluate;
    private String deliveryTime;
    private transient int storeImageId;

    public StoreMinute(int storeID,String StoreName, String StorePrice, String storeImage, String storeEvaluate, String deliveryTime) {
        this.storeID = storeID;
        this.storeName = StoreName;
        this.storePrice = StorePrice;
        this.storeImage = storeImage;
        this.storeEvaluate = storeEvaluate;
        this.deliveryTime = deliveryTime;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getSubtitle() {
        return storePrice;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public int getStoreImageId() {
        return storeImageId;
    }

    public void setStoreImageId(int storeImageId) {
        this.storeImageId = storeImageId;
    }

    public String getStoreEvaluate() { return  storeEvaluate; }

    public String getDeliveryTime() {
        return deliveryTime;
    }
}