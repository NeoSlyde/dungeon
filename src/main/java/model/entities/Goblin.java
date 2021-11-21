package model.entities;
import model.World;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;

public class Goblin extends Monster {

  public Goblin(Position position, World world) {
    super(position, new Size(0.5, 0.5), DirectedSprite.fromImagePaths(
        new String[] { "/monster/goblin/up0.png", "/monster/goblin/up1.png", "/monster/goblin/up2.png" },
        new String[] { "/monster/goblin/down0.png", "/monster/goblin/down1.png", "/monster/goblin/down2.png" },
        new String[] { "/monster/goblin/right0.png", "/monster/goblin/right1.png", "/monster/goblin/right2.png" },
        new String[] { "/monster/goblin/left0.png", "/monster/goblin/left1.png", "/monster/goblin/left2.png" }), world);
  }

  @Override
  public void update(double dt, World world) {
    world.getEntities().stream().filter(e -> e instanceof Player).forEach(e -> {
      if (e.getPosition().room.id == getPosition().room.id) {
        this.setMoving(true);
        if (e.distance(this) > 10) {
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
    return 1;
  }

}
