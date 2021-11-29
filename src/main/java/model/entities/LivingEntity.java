package model.entities;

import java.util.Optional;

import model.Inventory;
import model.Room;
import model.misc.Direction;
import model.misc.Vec2;

public abstract class LivingEntity extends Entity {
    private boolean moving = false;
    private Direction facingDirection = Direction.RIGHT;
    private Inventory inventory = new Inventory();
    private Optional<LivingEntity> inCombatWith = Optional.empty();
    private boolean running = false;
    private double health;

    public LivingEntity(Room room, Vec2 position, Vec2 size) {
        super(room, position, size);
    }

    @Override
    public boolean isUsable() {
        return false;
    }

    @Override
    public void use(LivingEntity user) {
    }

    public abstract double getMaxHealth();

    public abstract double getHealth();

    public abstract double getStrength();

    public abstract double getSpeed();

    public Inventory getInventory() {
        return inventory;
    }

    public Optional<LivingEntity> getInCombatWith() {
        return inCombatWith;
    }

    public void setInCombatWith(LivingEntity inCombatWith) {
        this.inCombatWith = Optional.ofNullable(inCombatWith);
    }

    public void withdrawFromCombat() {
        inCombatWith = Optional.empty();
    }

    public void setMoving(boolean m) {
        this.moving = m;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setFacingDirection(Direction d) {
        this.facingDirection = d;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void heal(double amount) {
        double newHealth = health + amount;
        newHealth = Math.min(getMaxHealth(), newHealth);
        setHealth(newHealth);
    }

    private void setHealth(double newHealth) {
        newHealth = Math.max(0, Math.min(getMaxHealth(), newHealth));
        this.health = newHealth;
    }

    @Override
    public void update(double dt) {
        setPosition(computeNextPos(dt));
    }

    private Vec2 getVelocity() {
        return moving ? facingDirection.getUnitVec2().withMagnitude(getSpeed()) : Vec2.ZERO;
    }

    private Vec2 computeNextPos(double dt) {
        var next = new Vec2(getPosition()).add(getVelocity().multiply(dt));
        next = next.withX(Math.max(0, Math.min(getRoom().size.x - getSize().x, next.x)));
        next = next.withY(Math.max(0, Math.min(getRoom().size.y - getSize().y, next.y)));
        return next;
    }
}
