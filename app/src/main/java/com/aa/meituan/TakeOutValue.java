package com.aa.meituan;

public class TakeOutValue {
    private String name;
    private double price;
    private int quantity;

    public TakeOutValue(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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
}
