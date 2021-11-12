package model.entities;

import model.Inventory;
import model.World;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Size;

import java.awt.image.BufferedImage;

public abstract class LivingEntity extends Entity {
  private double maxHealth;
  private double health;

  private double maxMana;
  private double mana;

  private Direction facingDirection = Direction.EAST;
  private boolean moving = false;

  private double speed;

  private Inventory inventory;

  public BufferedImage up0,up1,up2,down0,down1,down2,left0,left1,left2,right0,right1,right2;
  public int spriteCounter = 0;
  public int spriteNum = 0;
  public int framelimit = 2;


  public LivingEntity(Position position, Size size) {
    super(position, size);
  }

  @Override
  public boolean canBeUsed() {
    return false;
  }

  @Override
  public void update(double dt, World world) {
    if (isMoving()) {
      double dx = facingDirection.unitX() * dt;
      double dy = facingDirection.unitY() * dt;

      setPosition(new Position(getPosition().x + dx, getPosition().y + dy, getPosition().room));
    }
  }

  public double getMaxHealth() {
    return maxHealth;
  }

  public void setMaxHealth(double maxHealth) {
    if (maxHealth < 0)
      throw new IllegalArgumentException("maxHealth must be positive");

    this.maxHealth = maxHealth;
  }

  public double getHealth() {
    return health;
  }

  public void setHealth(double health) {
    if (health < 0 || health > maxHealth)
      throw new IllegalArgumentException("Invalid health value");

    this.health = health;
  }

  public double getMaxMana() {
    return maxMana;
  }

  public void setMaxMana(double maxMana) {
    if (maxMana < 0)
      throw new IllegalArgumentException("maxMana must be positive");

    this.maxMana = maxMana;
  }

  public double getMana() {
    return mana;
  }

  public void setMana(double mana) {
    if (mana < 0 || mana > maxMana)
      throw new IllegalArgumentException("Invalid mana value");

    this.mana = mana;
  }

  public Direction getFacingDirection() {
    return facingDirection;
  }

  public void setFacingDirection(Direction facingDirection) {
    this.facingDirection = facingDirection;
  }

  public boolean isMoving() {
    return moving;
  }

  public void setMoving(boolean moving) {
    this.moving = moving;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    if (speed < 0)
      throw new IllegalArgumentException("speed must be positive");

    this.speed = speed;
  }

  public Inventory getInventory() {
    return inventory;
  }

  
}
