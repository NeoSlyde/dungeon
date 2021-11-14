package model.entities;

import javafx.scene.canvas.GraphicsContext;
import model.Inventory;
import model.World;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Room;
import model.misc.Size;
import view.DirectedSprite;
import view.Drawable;

public abstract class LivingEntity extends Entity {
  private double maxHealth = 20;
  private double health = 20;

  private double maxMana = 20;
  private double mana = 20;

  private Direction facingDirection = Direction.EAST;
  private boolean moving = false;

  private Inventory inventory = new Inventory();
  public DirectedSprite sprite;

  public LivingEntity(Position position, Size size, DirectedSprite sprite) {
    super(position, size);
    this.sprite = sprite;
  }

  @Override
  public boolean canBeUsed() {
    return false;
  }

  @Override
  public void update(double dt, World world) {
    if(!isStopped()){
    sprite.setSpeed(getSpeed() * 0.7);
    if (isMoving()) {
      setPosition(computeNextPosition(dt, world));
      sprite.update(dt);
    } else {
      sprite.setIdle();
    }
    }
  }

  private Position computeNextPosition(double dt, World world) {
    var newX = getPosition().x + getFacingDirection().unitX() * dt * getSpeed() / 200;
    var newY = getPosition().y + getFacingDirection().unitY() * dt * getSpeed() / 200;
    var newPos = new Position(newX, newY, getPosition().room);
    if (newX < 0 || newY < 0 || newX >= Room.getSize().width || newY >= Room.getSize().height) {
      return getPosition();
    }
    for (var entity : world.getEntities()) {
      // If the entity collides with any other, we don't move
      // (i.e return the old position)
      if (!entity.collidesWith(getPosition(), getSize()) && entity.collidesWith(newPos, getSize()))
        return getPosition();
    }
    return newPos;
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    gc.drawImage(sprite.getImage(getFacingDirection()), Drawable.VIRTUAL_TO_PX * getPosition().x,
        Drawable.VIRTUAL_TO_PX * getPosition().y, Drawable.VIRTUAL_TO_PX * getSize().width,
        Drawable.VIRTUAL_TO_PX * getSize().height);
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
    return 1;
  }

  public Inventory getInventory() {
    return inventory;
  }
}
