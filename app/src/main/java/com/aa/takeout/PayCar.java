package com.aa.takeout;

import java.util.ArrayList;
import java.util.List;

public class PayCar {
    private List<TakeOutValue> items;
    private static PayCar instance;

    private PayCar() {
        items = new ArrayList<>();
    }

    public static synchronized PayCar getInstance() {
        if (instance == null) {
            instance = new PayCar();
        }
        return instance;
    }

    public void addItem(TakeOutValue item) {
        items.add(item);
    }

    public void removeItem(TakeOutValue item) {
        items.remove(item);
    }

    public List<TakeOutValue> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (TakeOutValue item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void clear() {
        items.clear();
    }
}