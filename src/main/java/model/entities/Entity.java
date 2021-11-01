package model.entities;

import model.Usable;
import model.World;
import model.misc.Position;
import model.misc.Size;
import view.View;

public abstract class Entity implements Usable {
  private Position position;
  private Size size;
  protected View view;

  public Entity(Position position, Size size, View view) {
    this.position = position;
    this.size = size;
    this.view = view;
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
