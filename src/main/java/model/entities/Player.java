package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;

public class Player extends LivingEntity {

  private double speed = 0.166;

  public Player(Position position) {
    super(position, new Size(1, 1));
  }

  public void setRunning() {
    this.speed = 0.332;
  }

  public void setWalking() {
    this.speed = 0.166;
  }

  @Override
  public void update(double dt, World world) {
    if (isMoving()) {
      var newX = getPosition().x + getFacingDirection().unitX() * dt * speed;
      var newY = getPosition().y + getFacingDirection().unitY() * dt * speed;
      if(newX+32 < 960 && newY+32 < 540 && newX > 0 && newY> 0) {
      setPosition(new Position(newX, newY, getPosition().room));
    }
    }
  }

}
