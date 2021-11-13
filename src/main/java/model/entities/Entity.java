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

  public boolean contains(Position pos) {
    return pos.x >= getPosition().x && pos.x <= getPosition().x + getSize().width && pos.y >= getPosition().y
        && pos.y <= getPosition().y + getSize().height && pos.room.equals(getPosition().room);
  }

  public boolean collidesWith(Position pos, Size size) {
    var topLeft = pos;
    var topRight = new Position(topLeft.x + size.width, topLeft.y, topLeft.room);
    var bottomLeft = new Position(topLeft.x, topLeft.y + size.height, topLeft.room);
    var bottomRight = new Position(topLeft.x + size.width, topLeft.y + size.height, topLeft.room);
    // A square collides with another <=> one of its corners is inside the other
    // square
    return contains(topLeft) || contains(topRight) || contains(bottomLeft) || contains(bottomRight);
  }
}
