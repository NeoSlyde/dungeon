package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;

public abstract class Monster extends LivingEntity {

  public Monster(Position position, Size size, DirectedSprite sprite) {
    super(position, size, sprite);
  }

  @Override
  public void update(double dt, World world) {
    throw new AssertionError("Unimplemented");
  }

}
