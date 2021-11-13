package model.entities;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Inventory;
import model.World;
import model.misc.Direction;
import model.misc.Position;
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
  private DirectedSprite sprite;

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
    sprite.setSpeed(getSpeed() * 0.7);
    if (isMoving()) {
      var newX = getPosition().x + getFacingDirection().unitX() * dt * getSpeed() / 200;
      var newY = getPosition().y + getFacingDirection().unitY() * dt * getSpeed() / 200;
      if (newX + getSize().width <= getPosition().room.getSize().width
          && newY + getSize().height <= getPosition().room.getSize().height && newX >= 0 && newY >= 0) {
        setPosition(new Position(newX, newY, getPosition().room));
      }
      sprite.update(dt);
    } else {
      sprite.setIdle();
    }
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    Image imagef = SwingFXUtils.toFXImage(sprite.getImage(getFacingDirection()), null);
    gc.drawImage(imagef, Drawable.VIRTUAL_TO_PX * getPosition().x, Drawable.VIRTUAL_TO_PX * getPosition().y,
        Drawable.VIRTUAL_TO_PX * getSize().width, Drawable.VIRTUAL_TO_PX * getSize().height);
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
