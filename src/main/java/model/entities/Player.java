package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;
import view.View;

public class Player extends LivingEntity {
  public Player(Position position, Size size, View view) {
    super(position, size, view);
  }

  @Override
  public void update(double dt, World world) {
    throw new AssertionError("Unimplemented");
  }
}
