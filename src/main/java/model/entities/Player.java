package model.entities;

import javafx.scene.canvas.GraphicsContext;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;
import view.Drawable;

public class Player extends LivingEntity {

  private boolean running = false;

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
  public double getSpeed() {
    return running ? 2 : 1;
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    gc.drawImage(sprite.getImage(getFacingDirection()), Drawable.VIRTUAL_TO_PX * (getPosition().x - 0.25),
        Drawable.VIRTUAL_TO_PX * (getPosition().y - 0.25), Drawable.VIRTUAL_TO_PX * (getSize().width+0.5),
        Drawable.VIRTUAL_TO_PX * (getSize().height+0.5));
  }
}
