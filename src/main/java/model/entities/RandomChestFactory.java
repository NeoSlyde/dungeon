package model.entities;

import model.Room;
import model.items.HealingPotion;
import model.misc.Vec2;

public class RandomChestFactory implements EntityFactory {
    @Override
    public Chest generate(Room room, Vec2 position) {
        var chest = new Chest(room, position);
        chest.getInventory().addItem(new HealingPotion(), 4);
        return chest;
    }
}
