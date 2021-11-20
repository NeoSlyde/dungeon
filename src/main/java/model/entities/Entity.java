package model.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Usable;
import model.World;
import model.misc.Position;
import model.misc.Size;
import view.Drawable;

public abstract class Entity implements Usable, Drawable {
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

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    gc.setFill(Color.WHITE);
    gc.fillRect(getPosition().x * Drawable.VIRTUAL_TO_PX, getPosition().y * Drawable.VIRTUAL_TO_PX,
        Drawable.VIRTUAL_TO_PX * getSize().width, Drawable.VIRTUAL_TO_PX * getSize().height);
  }

  public boolean collidesWith(Position pos, Size size) {
    return this.position.x < pos.x + size.width && this.position.x + this.size.width > pos.x
        && this.position.y < pos.y + size.height && this.position.y + this.size.height > pos.y
        && getPosition().room.equals(pos.room);
  }

  public double distance(Entity other) {
    return Math.sqrt(Math.pow(this.position.x - other.position.x, 2) + Math.pow(this.position.y - other.position.y, 2));
  }
}
