package model.entities;

import model.Usable;
import model.World;
import model.misc.Position;
import model.misc.Size;

public abstract class Entity implements Usable {
  private Position position;
  private Size size;

  public Entity(Position position, Size size) {
    this.position = position;
    this.size = size;
  }

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
