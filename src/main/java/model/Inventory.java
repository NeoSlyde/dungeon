package model;

import java.util.HashMap;
import java.util.Map;

import model.items.Item;

public class Inventory {

    private Map<Item, Integer> items = new HashMap<>();
    private int capacity = 12;

    public void addItem(Item item, int amount) {
        if (items.size() < capacity || items.keySet().contains(item)) {
            items.compute(item, (_item, i) -> i == null ? amount : i + amount);
        }
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void removeItem(Item item, int amount) {
        items.computeIfPresent(item, (k, v) -> v - amount > 0 ? v - amount : 0);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public int getCapacity() {
        return capacity;
    }

    public void addAll(Inventory inv2) {
        inv2.items.forEach(this::addItem);
    }
}
