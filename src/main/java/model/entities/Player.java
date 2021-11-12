package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;

public class Player extends LivingEntity {
  public Player(Position position) {
    super(position, new Size(1, 1));
  }

  @Override
  public void update(double dt, World world) {
    if (isMoving()) {
      final double speed = 0.166;
      var newX = getPosition().x + getFacingDirection().unitX() * dt * speed;
      var newY = getPosition().y + getFacingDirection().unitY() * dt * speed;
      setPosition(new Position(newX, newY, getPosition().room));
    }
  }

}
