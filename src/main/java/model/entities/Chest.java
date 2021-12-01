package model.entities;

import model.Inventory;
import model.Room;
import model.misc.Vec2;

public class Chest extends Entity {
    private Inventory inventory = new Inventory();

    public Chest(Room room, Vec2 position) {
        super(room, position, new Vec2(1, 1));
    }

    @Override
    public boolean isUsable() {
        return true;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void use(LivingEntity user) {
        user.getInventory().addAll(inventory);
        getRoom().removeEntity(this);
    }
}
