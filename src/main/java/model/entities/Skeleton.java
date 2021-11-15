package model.entities;
import model.World;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;

public class Skeleton extends Monster {

  public Skeleton(Position position) {
    super(position, new Size(0.5, 0.5), DirectedSprite.fromImagePaths(
        new String[] { "/monster/skeleton/up0.png", "/monster/skeleton/up1.png", "/monster/skeleton/up2.png" },
        new String[] { "/monster/skeleton/down0.png", "/monster/skeleton/down1.png", "/monster/skeleton/down2.png" },
        new String[] { "/monster/skeleton/right0.png", "/monster/skeleton/right1.png", "/monster/skeleton/right2.png" },
        new String[] { "/monster/skeleton/left0.png", "/monster/skeleton/left1.png", "/monster/skeleton/left2.png" }));
  }

  @Override
  public void update(double dt, World world) {
    world.getEntities().stream().filter(e -> e instanceof Player).forEach(e -> {
      if (e.getPosition().room.id == getPosition().room.id) {
        this.setMoving(true);
        if (e.distance(this) > 5) {
          this.setMoving(false);
        } else if (this.distance(e) < 1 && isMoving()) {
          this.setMoving(false);
        } else if (e.getPosition().y + 0.1 < this.getPosition().y) {
          this.setFacingDirection(Direction.NORTH);
        } else if (e.getPosition().y - 0.1 > this.getPosition().y) {
          this.setFacingDirection(Direction.SOUTH);
        } else if (e.getPosition().x + 0.1 < this.getPosition().x) {
          this.setFacingDirection(Direction.WEST);
        } else if (e.getPosition().x - 0.1 > this.getPosition().x) {
          this.setFacingDirection(Direction.EAST);
        }
      }
    });

    super.update(dt, world);
  }

  @Override
  public double getSpeed() {
    return 0.5;
  }

}
