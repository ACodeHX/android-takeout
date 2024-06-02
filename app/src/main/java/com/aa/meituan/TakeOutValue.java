package com.aa.meituan;

public class TakeOutValue {
    private String FoodID;
    private String name;
    private double price;
    private int quantity;
    private String image;

    public TakeOutValue(String FoodID, String name, double price, int quantity, String image) {
        this.FoodID = FoodID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public String getFoodID() {
        return FoodID;
    }

    public void setFoodID(String foodID) {
        this.FoodID = foodID;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
