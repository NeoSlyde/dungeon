package model;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<Item, Integer> items = new HashMap<>();

    public void addItem(Item item, int amount) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + amount);
        } else {
            items.put(item, amount);
        }
    }

    public void removeItem(Item item, int amount) {
        items.computeIfPresent(item, (k, v) -> v - amount > 0 ? v - amount : null);
    }

}
