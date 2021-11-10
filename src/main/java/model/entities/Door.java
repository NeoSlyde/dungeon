package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;
import view.View;

public class Door extends Entity{

    Door destination;

    public Door(Position position, Size size, View view) {
        super(position, size, view);
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
