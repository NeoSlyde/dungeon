package model.entities;

import java.util.Random;

import model.Room;
import model.items.HealingPotion;
import model.items.ManaPotion;
import model.misc.Vec2;

public class RandomChestFactory implements EntityFactory {
    private final Random random;

    public RandomChestFactory(Random random) {
        this.random = random;
    }

    @Override
    public Chest generate(Room room, Vec2 position) {
        var chest = new Chest(room, position);
        chest.getInventory().addItem(new HealingPotion(), random.nextInt(9));
        chest.getInventory().addItem(new ManaPotion(), random.nextInt(9));
        return chest;
    }
}
