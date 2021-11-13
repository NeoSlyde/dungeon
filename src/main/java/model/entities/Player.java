package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;

public class Player extends LivingEntity {
  private DirectedSprite sprite = DirectedSprite.fromImagePaths(
      new String[] { "/player/up0.png", "/player/up1.png", "/player/up2.png" },
      new String[] { "/player/down0.png", "/player/down1.png", "/player/down2.png" },
      new String[] { "/player/right0.png", "/player/right1.png", "/player/right2.png" },
      new String[] { "/player/left0.png", "/player/left1.png", "/player/left2.png" });

  private boolean running = false;

  public Player(Position position) {
    super(position, new Size(1, 1));
  }

  public void setRunning(boolean running) {
    this.running = running;
  }

  public double getSpeed() {
    return running ? 0.332 : 0.166;
  }

  @Override
  public void update(double dt, World world) {
    sprite.setSpeed(getSpeed() * 3);
    if (isMoving()) {
      var newX = getPosition().x + getFacingDirection().unitX() * dt * getSpeed();
      var newY = getPosition().y + getFacingDirection().unitY() * dt * getSpeed();
      if (newX + 32 < 960 && newY + 32 < 540 && newX > 0 && newY > 0) {
        setPosition(new Position(newX, newY, getPosition().room));
      }
      sprite.update(dt);
    } else {
      sprite.setIdle();
    }
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    Image imagef = SwingFXUtils.toFXImage(sprite.getImage(getFacingDirection()), null);
    gc.drawImage(imagef, getPosition().x, getPosition().y, 32, 32);
  }
}
