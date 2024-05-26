package com.aa.meituan;

public class StoreMinute {
    private String title;
    private String subtitle;
    private int imageResourceId;

    public StoreMinute(String title, String subtitle, int imageResourceId) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}