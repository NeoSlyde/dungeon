package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;

public class Player extends LivingEntity {
  public Player(Position position) {
    super(position, new Size(1, 1));
  }

  @Override
  public void update(double dt, World world) {
    throw new AssertionError("Unimplemented");
  }

}
