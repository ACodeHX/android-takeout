package com.aa.meituan;

import java.util.ArrayList;
import java.util.List;

public class PayCat {
    private List<TakeOutValue> items;
    private static PayCat instance;

    private PayCat() {
        items = new ArrayList<>();
    }

    public static synchronized PayCat getInstance() {
        if (instance == null) {
            instance = new PayCat();
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