package model.entities;

import java.util.Optional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Inventory;
import model.World;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Room;
import model.misc.Size;
import view.DirectedSprite;
import view.Drawable;

public abstract class LivingEntity extends Entity {
  private double maxHealth = 100;
  private double health = 100;

  protected double strength = 8;

  private Direction facingDirection = Direction.EAST;
  private boolean moving = false;

  private Inventory inventory = new Inventory();
  protected DirectedSprite sprite;

  private Optional<LivingEntity> combatting = Optional.empty();

  public LivingEntity(Position position, Size size, DirectedSprite sprite, World world) {
    super(position, size, world);
    this.sprite = sprite;
  }

  @Override
  public boolean canBeUsed() {
    return true;
  }

  @Override
  public void use(LivingEntity entity) {
    setInCombat(Optional.of(entity));
  }

  public void attack() {
    getCombatting().ifPresent(entity -> entity.setHealth(Math.max(0, entity.getHealth() - strength)));
  }

  public void scheduleAttackBack() {
  }

  @Override
  public void update(double dt, World world) {
    sprite.setSpeed(getSpeed() * 0.7);
    if (isMoving()) {
      setPosition(computeNextPosition(dt, world));
      sprite.update(dt);
    } else {
      sprite.setIdle();
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
    gc.drawImage(sprite.getImage(getFacingDirection()), Drawable.VIRTUAL_TO_PX * (getPosition().x - 0.25),
        Drawable.VIRTUAL_TO_PX * (getPosition().y - 0.25), Drawable.VIRTUAL_TO_PX * (getSize().width + 0.5),
        Drawable.VIRTUAL_TO_PX * (getSize().height + 0.5));
    gc.setStroke(Color.GREEN);
    gc.strokeText(this.getHealth() + " HP", Drawable.VIRTUAL_TO_PX * getPosition().x - 15,
        Drawable.VIRTUAL_TO_PX * getPosition().y - 25);
    gc.setStroke(Color.BLUE);
    gc.strokeText(this.strength + " STR", Drawable.VIRTUAL_TO_PX * getPosition().x - 16,
        Drawable.VIRTUAL_TO_PX * getPosition().y - 10);
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

  public double getStrength() {
    return strength;
  }

  public void setHealth(double health) {
    if (health < 0 || health > maxHealth)
      throw new IllegalArgumentException("invalid health value");

    this.health = health;
  }

  public void setStrength(double strength) {
    if (strength < 0)
      throw new IllegalArgumentException("invalid strength value");

    this.strength = strength;
  }

  public Direction getFacingDirection() {
    return facingDirection;
  }

  public void setFacingDirection(Direction facingDirection) {
    this.facingDirection = facingDirection;
  }

  public boolean isMoving() {
    return moving && getCombatting().isEmpty();
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

  public void setInCombat(Optional<LivingEntity> adversary) {
    this.combatting = adversary;
    adversary.ifPresent(a -> a.combatting = Optional.of(this));
  }

  public Optional<LivingEntity> getCombatting() {
    return combatting;
  }
}
