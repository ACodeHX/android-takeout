package com.aa.meituan;

public class StoreMinute {
    private String storeName;
    private String subtitle;
    private int imageResourceId;

    public StoreMinute(String title, String subtitle, int imageResourceId) {
        this.storeName = title;
        this.subtitle = subtitle;
        this.imageResourceId = imageResourceId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}