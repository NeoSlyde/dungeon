package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {
  private List<Item> items = new ArrayList<>();
  private Optional<Integer> capacity;

  /**
   * Constructs an inventory with unlimited capacity
   */
  public Inventory() {
    capacity = Optional.empty();
  }

  public Inventory(int capacity) {
    this.capacity = Optional.of(capacity);
  }

  public void add(Item item) {
    if (isFull())
      throw new IllegalStateException("Inventory is full");

    items.add(item);
  }

  public boolean isFull() {
    return capacity.isPresent() && items.size() >= capacity.get();
  }

  public Optional<Integer> getCapacity() {
    return capacity;
  }
}
