package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;

public abstract class Monster extends LivingEntity {

    public Monster(Position position, Size size) {
        super(position, size);
    } 

    @Override
    public void update(double dt, World world) {
    throw new AssertionError("Unimplemented");
  }
    
}
