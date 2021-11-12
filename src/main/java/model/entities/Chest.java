package model.entities;

import model.Inventory;
import model.World;
import model.misc.Position;
import model.misc.Size;

public class Chest extends Entity{

    private Inventory inventory;

    public Chest(Position position, Size size) {
        super(position, size);
    }

    @Override
    public boolean canBeUsed() {
        return true;
    }

    @Override
    public void use(LivingEntity entity) {
        // TODO: Open chest in UI
    }

    @Override
    public void update(double dt, World world) {
        //Do nothing
    }

    public Inventory getInventory() {
        return inventory;
    }
    
}
