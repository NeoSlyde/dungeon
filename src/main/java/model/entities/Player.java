package model.entities;

import javafx.scene.canvas.GraphicsContext;
import model.World;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;
import view.Drawable;

public class Player extends LivingEntity {

  private boolean running = false;

  private Entity canBeUsed = null;

  public Player(Position position) {
    super(position, new Size(0.5, 0.5),
        DirectedSprite.fromImagePaths(new String[] { "/player/up0.png", "/player/up1.png", "/player/up2.png" },
            new String[] { "/player/down0.png", "/player/down1.png", "/player/down2.png" },
            new String[] { "/player/right0.png", "/player/right1.png", "/player/right2.png" },
            new String[] { "/player/left0.png", "/player/left1.png", "/player/left2.png" }));
  }

  public void setRunning(boolean running) {
    this.running = running;
  }

  @Override
  public void update(double dt, World world) {
    super.update(dt, world);
    canBeUsed = null;
    for (var entity : world.getEntities()) {

      if (!entity.canBeUsed()) {
        continue;
      }

      if (getFacingDirection() == Direction.NORTH
          && entity.collidesWith(new Position(getPosition().x, getPosition().y - 0.5, getPosition().room), getSize())) {
        System.out.println("UP");
        canBeUsed = entity;
      } else if (getFacingDirection() == Direction.SOUTH
          && entity.collidesWith(new Position(getPosition().x, getPosition().y + 0.5, getPosition().room), getSize())) {
        System.out.println("DOWN");
        canBeUsed = entity;
      } else if (getFacingDirection() == Direction.EAST
          && entity.collidesWith(new Position(getPosition().x + 0.5, getPosition().y, getPosition().room), getSize())) {
        System.out.println("RIGHT");
        canBeUsed = entity;
      } else if (getFacingDirection() == Direction.WEST
          && entity.collidesWith(new Position(getPosition().x - 0.5, getPosition().y, getPosition().room), getSize())) {
        System.out.println("LEFT");
        canBeUsed = entity;
      }
    }
    System.out.println(canBeUsed);
  }

  public void useFacing() {
    if (canBeUsed != null) {
      canBeUsed.use(this);
    }
  }

  @Override
  public double getSpeed() {
    return running ? 2 : 1;
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    gc.drawImage(sprite.getImage(getFacingDirection()), Drawable.VIRTUAL_TO_PX * (getPosition().x - 0.25),
        Drawable.VIRTUAL_TO_PX * (getPosition().y - 0.25), Drawable.VIRTUAL_TO_PX * (getSize().width + 0.5),
        Drawable.VIRTUAL_TO_PX * (getSize().height + 0.5));
  }
}
