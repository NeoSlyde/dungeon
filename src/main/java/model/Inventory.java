package model;

import java.util.HashMap;
import java.util.Map;

import model.items.Item;

public class Inventory {

    private Map<Item, Integer> items = new HashMap<>();
    private int capacity = 12;

    public void addItem(Item item, int amount) {
        // ensure inventory has capacity
        if (items.size() < capacity) {
            // if item is already in inventory, add to amount
            if (items.containsKey(item)) {
                items.put(item, items.get(item) + amount);
            } else {
                // if item is not in inventory, add item and amount
                items.put(item, amount);
            }
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

}
