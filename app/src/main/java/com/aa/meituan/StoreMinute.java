package com.aa.meituan;

public class StoreMinute {
    private String storeID;                  //商店id
    private String storeName;               //商店名称
    private String soldMonthly;             //月售
    private String storeImage;              //图片
    private String storeEvaluate;           //商店评价
    private String deliveryTime;            //派送时间
    private transient int storeImageId;

    public StoreMinute(String storeID, String StoreName, String soldMonthly, String storeImage, String storeEvaluate, String deliveryTime) {
        this.storeID = storeID;
        this.storeName = StoreName;
        this.soldMonthly = soldMonthly;
        this.storeImage = storeImage;
        this.storeEvaluate = storeEvaluate;
        this.deliveryTime = deliveryTime;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getSubtitle() {
        return soldMonthly;
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