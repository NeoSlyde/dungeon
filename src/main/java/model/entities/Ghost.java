package model.entities;
import model.World;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Room;
import model.misc.Size;
import view.DirectedSprite;

public class Ghost extends Monster {

  public Ghost(Position position, World world) {
    super(position, new Size(0.5, 0.5), DirectedSprite.fromImagePaths(
        new String[] { "/monster/ghost/up0.png", "/monster/ghost/up1.png", "/monster/ghost/up2.png" },
        new String[] { "/monster/ghost/down0.png", "/monster/ghost/down1.png", "/monster/ghost/down2.png" },
        new String[] { "/monster/ghost/right0.png", "/monster/ghost/right1.png", "/monster/ghost/right2.png" },
        new String[] { "/monster/ghost/left0.png", "/monster/ghost/left1.png", "/monster/ghost/left2.png" }), world);
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
    sprite.setSpeed(getSpeed() * 0.7);
    if (isMoving()) {
      setPosition(computeNextPositionGhost(dt, world));
      sprite.update(dt);
    } else {
      sprite.setIdle();
    }
    
  }

  @Override
  public double getSpeed() {
    return 0.5;
  }

  private Position computeNextPositionGhost(double dt, World world) {
    var newX = getPosition().x + getFacingDirection().unitX() * dt * getSpeed() / 200;
    var newY = getPosition().y + getFacingDirection().unitY() * dt * getSpeed() / 200;
    var newPos = new Position(newX, newY, getPosition().room);
    if (newX < 0 || newY < 0 || newX >= Room.getSize().width || newY >= Room.getSize().height) {
      return getPosition();
    }
    return newPos;
  }



}
