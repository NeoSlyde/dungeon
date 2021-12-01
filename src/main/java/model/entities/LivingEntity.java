package model.entities;

import java.util.Optional;
import java.util.stream.Stream;

import model.Inventory;
import model.Room;
import model.attacks.Attack;
import model.misc.Direction;
import model.misc.Vec2;

public abstract class LivingEntity extends Entity {
    private boolean moving = false;
    private Direction facingDirection = Direction.RIGHT;
    private Inventory inventory = new Inventory();
    private Optional<LivingEntity> inCombatWith = Optional.empty();
    private boolean running = false;
    private double health = 100, maxHealth = 100;
    private double mana = 100, maxMana = 100;

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

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getHealth() {
        return health;
    }

    public abstract double getStrength();

    public abstract double getSpeed();

    public abstract String getName();

    public double getMana() {
        return mana;
    }

    public void setMana(double mana) {
        this.mana = Math.max(0, Math.min(getMaxMana(), mana));
    }

    public double getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(double maxMana) {
        this.maxMana = maxMana;
    }

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

    public Stream<Entity> getFacingEntities() {
        return getRoom().getEntities().stream()
                .filter(e -> e != this)
                .filter(e -> e.collidesWith(getPosition().add(getFacingDirection()
                        .getUnitVec2()), getSize()));
    }

    public void setHealth(double health) {
        this.health = Math.max(0, Math.min(getMaxHealth(), health));
    }

    public boolean canDoAttack(Attack attack) {
        return getMana() >= attack.getMana();
    }

    public void attack(LivingEntity target, Attack attack) {
        target.setHealth(target.getHealth() - getStrength() * attack.getStrength());
        setMana(getMana() - attack.getMana());
    }

    public void update(double dt) {
        final var nextPos = computeNextPos(dt);
        if (getRoom().getEntities().stream().filter(e -> e != this).noneMatch(e -> e.collidesWith(nextPos, getSize())))
            setPosition(nextPos);
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
