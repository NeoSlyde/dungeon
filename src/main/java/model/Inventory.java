package model;

import java.util.HashMap;
import java.util.Map;

import model.items.Pickaxe;

public class Inventory {
  private Map<Item, Integer> items = new HashMap<>();

  public Inventory() {
  }

  public void add(Item item) {
    items.compute(item, (k, v) -> v == null ? 1 : v + 1);
  }

  public void remove(Item item) {
    items.compute(item, (k, v) -> v == null ? null : v - 1 == 0 ? null : v - 1);
  }

  public Map<Item, Integer> getItems() {
    return items;
  }

  public void addAll(Inventory other) {
    for (Item item : other.getItems().keySet()) {
      //if item is a pickaxe
      if (item instanceof Pickaxe) {
        //if item is already in inventory
        if (items.containsKey(item)) {
          //add the uses of the item
          items.put(item, items.get(item) + other.getItems().get(item));
        } else {
          //add the item to the inventory
          items.put(item, other.getItems().get(item));
        }
      }

      
      add(item);
    }
  }

  public Pickaxe getPickaxe() {
    return items.keySet().stream().filter(i -> i instanceof Pickaxe).map(i -> (Pickaxe) i).findAny().orElse(null);
  }
}
