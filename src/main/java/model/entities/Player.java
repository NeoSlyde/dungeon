package model.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.World;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;
import view.Drawable;

public class Player extends LivingEntity {

  private boolean running = false;

  private Entity canBeUsed = null;

  private boolean isInventoryOpen = false;

  private boolean isInCombat = false;

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
        canBeUsed = entity;
      } else if (getFacingDirection() == Direction.SOUTH
          && entity.collidesWith(new Position(getPosition().x, getPosition().y + 0.5, getPosition().room), getSize())) {
        canBeUsed = entity;
      } else if (getFacingDirection() == Direction.EAST
          && entity.collidesWith(new Position(getPosition().x + 0.5, getPosition().y, getPosition().room), getSize())) {
        canBeUsed = entity;
      } else if (getFacingDirection() == Direction.WEST
          && entity.collidesWith(new Position(getPosition().x - 0.5, getPosition().y, getPosition().room), getSize())) {
        canBeUsed = entity;
      }
    }
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

  public void openInventory() { 
    isInventoryOpen = true;
  }

  public void closeInventory() { 
    isInventoryOpen = false;
  }

  public boolean isInventoryOpen() {
    return isInventoryOpen;
  }

  public void setInCombat(boolean isInCombat) {
    this.isInCombat = isInCombat;
  }

  public boolean isInCombat() {
    return isInCombat;
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    gc.drawImage(sprite.getImage(getFacingDirection()), Drawable.VIRTUAL_TO_PX * (getPosition().x - 0.25),
        Drawable.VIRTUAL_TO_PX * (getPosition().y - 0.25), Drawable.VIRTUAL_TO_PX * (getSize().width + 0.5),
        Drawable.VIRTUAL_TO_PX * (getSize().height + 0.5));
    gc.setStroke(Color.GREEN);
    gc.strokeText(Double.toString(this.getHealth()) + " HP", Drawable.VIRTUAL_TO_PX * (getPosition().x) -15, Drawable.VIRTUAL_TO_PX * (getPosition().y) - 25);
    gc.setStroke(Color.BLUE);
    gc.strokeText(Double.toString(this.getMana()) + " MP", Drawable.VIRTUAL_TO_PX * (getPosition().x) -16, Drawable.VIRTUAL_TO_PX * (getPosition().y) - 10);
  }
}
