package com.aa.takeout;

public class StoreMinute {
    private String storeID;                  //商店id
    private String storeName;               //商店名称
    private String soldMonthly;             //月售
    private String storeImage;              //图片
    private String storeEvaluate;           //商店评价
    private String deliveryTime;            //派送时间
    private int deliveryFee;                //配送费
    private transient int storeImageId;

    public StoreMinute(String storeID, String StoreName, String soldMonthly, String storeImage, String storeEvaluate, String deliveryTime, int deliveryFee) {
        this.storeID = storeID;
        this.storeName = StoreName;
        this.soldMonthly = soldMonthly;
        this.storeImage = storeImage;
        this.storeEvaluate = storeEvaluate;
        this.deliveryTime = deliveryTime;
        this.deliveryFee = deliveryFee;
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

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}