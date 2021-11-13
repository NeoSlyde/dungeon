package view;

import javafx.scene.image.WritableImage;
import model.misc.Direction;

// The first sprite of each direction is the idle image.
// The rest is the animated state.
public class DirectedSprite {
  private WritableImage[] northSprites;
  private WritableImage[] southSprites;
  private WritableImage[] eastSprites;
  private WritableImage[] westSprites;

  private double spriteCounter = 0;
  private int spriteNum = 0;

  private double speed = 1;

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public static DirectedSprite fromImagePaths(String[] northSprites, String[] southSprites, String[] eastSprites,
      String[] westSprites) {
    var ds = new DirectedSprite();
    try {

      ds.northSprites = new WritableImage[northSprites.length];
      ds.southSprites = new WritableImage[northSprites.length];
      ds.eastSprites = new WritableImage[northSprites.length];
      ds.westSprites = new WritableImage[northSprites.length];
      for (int i = 0; i < northSprites.length; i++)
        ds.northSprites[i] = ResourceManager.INSTANCE.getWritableImage(northSprites[i]);
      for (int i = 0; i < southSprites.length; i++)
        ds.southSprites[i] = ResourceManager.INSTANCE.getWritableImage(southSprites[i]);
      for (int i = 0; i < eastSprites.length; i++)
        ds.eastSprites[i] = ResourceManager.INSTANCE.getWritableImage(eastSprites[i]);
      for (int i = 0; i < westSprites.length; i++)
        ds.westSprites[i] = ResourceManager.INSTANCE.getWritableImage(westSprites[i]);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return ds;
  }

  public WritableImage getImage(Direction direction) {
    switch (direction) {
    case NORTH:
      return northSprites[spriteNum];
    case SOUTH:
      return southSprites[spriteNum];
    case EAST:
      return eastSprites[spriteNum];
    case WEST:
      return westSprites[spriteNum];
    default:
      return null;
    }
  }

  public void setIdle() {
    spriteNum = 0;
  }

  public void update(double dt) {
    spriteCounter += dt;
    if (spriteCounter * speed > 80) {
      spriteNum = (spriteNum) % 2 + 1;
      spriteCounter = 0;
    }
  }
}
