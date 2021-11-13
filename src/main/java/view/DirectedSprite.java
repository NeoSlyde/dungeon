package view;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import model.misc.Direction;

// The first sprite of each direction is the idle image.
// The rest is the animated state.
public class DirectedSprite {
  private BufferedImage[] northSprites;
  private BufferedImage[] southSprites;
  private BufferedImage[] eastSprites;
  private BufferedImage[] westSprites;

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

      ds.northSprites = new BufferedImage[northSprites.length];
      ds.southSprites = new BufferedImage[northSprites.length];
      ds.eastSprites = new BufferedImage[northSprites.length];
      ds.westSprites = new BufferedImage[northSprites.length];
      for (int i = 0; i < northSprites.length; i++)
        ds.northSprites[i] = ImageIO.read(DirectedSprite.class.getResourceAsStream(northSprites[i]));
      for (int i = 0; i < southSprites.length; i++)
        ds.southSprites[i] = ImageIO.read(DirectedSprite.class.getResourceAsStream(southSprites[i]));
      for (int i = 0; i < eastSprites.length; i++)
        ds.eastSprites[i] = ImageIO.read(DirectedSprite.class.getResourceAsStream(eastSprites[i]));
      for (int i = 0; i < westSprites.length; i++)
        ds.westSprites[i] = ImageIO.read(DirectedSprite.class.getResourceAsStream(westSprites[i]));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return ds;
  }

  public BufferedImage getImage(Direction direction) {
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
