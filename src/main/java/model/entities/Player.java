package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;
import java.io.IOException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player extends LivingEntity {

  private double speed = 0.166;

  public Player(Position position) {
    super(position, new Size(1, 1));
  }

  public void setRunning() {
    this.speed = 0.332;
    this.framelimit = 2;
  }

  public void setWalking() {
    this.speed = 0.166;
    this.framelimit = 5;
  }

  public void getPlayerImage() {
    try {
      up0 = ImageIO.read(getClass().getResourceAsStream("/player/up0.png"));
      up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
      up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
      down0 = ImageIO.read(getClass().getResourceAsStream("/player/down0.png"));
      down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
      down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
      left0 = ImageIO.read(getClass().getResourceAsStream("/player/left0.png"));
      left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
      left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
      right0 = ImageIO.read(getClass().getResourceAsStream("/player/right0.png"));
      right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
      right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(double dt, World world) {
    if (isMoving()) {
      var newX = getPosition().x + getFacingDirection().unitX() * dt * speed;
      var newY = getPosition().y + getFacingDirection().unitY() * dt * speed;
      if (newX + 32 < 960 && newY + 32 < 540 && newX > 0 && newY > 0) {
        setPosition(new Position(newX, newY, getPosition().room));
      }

      spriteCounter++;
      if (spriteCounter > framelimit) {
        spriteNum = (spriteNum) % 2 + 1;
        spriteCounter = 0;
      }
    } else {
      spriteNum = 0;
    }
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    BufferedImage image = null;
    switch (getFacingDirection()) {
    case NORTH:
      if (spriteNum == 0) {
        image = up0;
      } else if (spriteNum == 1) {
        image = up1;
      } else if (spriteNum == 2) {
        image = up2;
      }
      break;
    case SOUTH:
      if (spriteNum == 0) {
        image = down0;
      } else if (spriteNum == 1) {
        image = down1;
      } else if (spriteNum == 2) {
        image = down2;
      }
      break;
    case EAST:
      if (spriteNum == 0) {
        image = right0;
      } else if (spriteNum == 1) {
        image = right1;
      } else if (spriteNum == 2) {
        image = right2;
      }
      break;
    case WEST:
      if (spriteNum == 0) {
        image = left0;
      } else if (spriteNum == 1) {
        image = left1;
      } else if (spriteNum == 2) {
        image = left2;
      }
      break;
    }
    Image imagef = SwingFXUtils.toFXImage(image, null);
    gc.drawImage(imagef, getPosition().x, getPosition().y, 32, 32);
    /*
     * gc.fillRect(getPosition().x, getPosition().y, Drawable.VIRTUAL_TO_PX *
     * getSize().width, Drawable.VIRTUAL_TO_PX * getSize().height);
     */
  }

}
