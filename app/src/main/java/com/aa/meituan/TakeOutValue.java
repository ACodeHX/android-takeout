package com.aa.meituan;

public class TakeOutValue {
    private int ID;
    private String name;
    private double price;
    private int quantity;
    private String imageUrl; // 添加图像 URL 字段
    private String image;
    private int imageID;

    public TakeOutValue(int ID,String name, double price, int quantity, String image) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
}
