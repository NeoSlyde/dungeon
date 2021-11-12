package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;

public class Door extends Entity{

    Door destination;

    public Door(Position position, Size size) {
        super(position, size);
    }

    @Override
    public boolean canBeUsed() {
        return true;
    }

    @Override
    public void use(LivingEntity entity) {
        entity.setPosition(destination.getPosition());
    }

    @Override
    public void update(double dt, World world) {
        //Do nothing
    }
    
}
