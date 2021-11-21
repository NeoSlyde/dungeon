package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;

public abstract class Monster extends LivingEntity {
  public Monster(Position position, Size size, DirectedSprite sprite, World world) {
    super(position, size, sprite, world);
  }

  @Override
  public boolean canBeUsed() {
    return true;
  }

  @Override
  public void scheduleAttackBack() {
    new Thread(() -> {
      try {
        Thread.sleep(300);
        getCombatting().ifPresent(entity -> entity.setHealth(Math.max(0, entity.getHealth() - strength)));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }
}
