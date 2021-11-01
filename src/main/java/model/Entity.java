package model;

import model.misc.Position;
import model.misc.Size;

public abstract class Entity {
  private Position position;
  private Size size;

  public abstract void update(double dt, World world);

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public Size getSize() {
    return size;
  }
}
