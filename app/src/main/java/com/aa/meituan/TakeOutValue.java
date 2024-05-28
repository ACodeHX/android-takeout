package com.aa.meituan;

public class TakeOutValue {
    private String name;
    private double price;
    private int quantity;
    private String imageUrl; // 添加图像 URL 字段
    private String image;
    private int imageID;

    public TakeOutValue(String name, double price, int quantity, String image, int imageID) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.imageID = imageID;
        //this.imageUrl = imageUrl;

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImageID() {
        return imageID;
    }
    public void setImageID() {
        this.imageID = imageID;
    }
}
