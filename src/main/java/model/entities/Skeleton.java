package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;

public class Skeleton extends Monster {

  public Skeleton(Position position) {
    super(position, new Size(1, 1), DirectedSprite.fromImagePaths(
        new String[] { "/monster/skeleton/up0.png", "/monster/skeleton/up1.png", "/monster/skeleton/up2.png" },
        new String[] { "/monster/skeleton/down0.png", "/monster/skeleton/down1.png", "/monster/skeleton/down2.png" },
        new String[] { "/monster/skeleton/right0.png", "/monster/skeleton/right1.png", "/monster/skeleton/right2.png" },
        new String[] { "/monster/skeleton/left0.png", "/monster/skeleton/left1.png", "/monster/skeleton/left2.png" }));
  }

  @Override
  public void update(double dt, World world) {

  }
}
