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

    @Override
    public void update(double dt) {
        setPosition(computeNextPos(dt));
    }

    private Vec2 getVelocity() {
        return moving ? facingDirection.getUnitVec2().withMagnitude(getSpeed()) : Vec2.ZERO;
    }

    private Vec2 computeNextPos(double dt) {
        return new Vec2(getPosition()).add(getVelocity().multiply(dt));
    }
}
