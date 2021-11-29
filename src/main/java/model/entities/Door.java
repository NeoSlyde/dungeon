package model.entities;

import java.util.Random;

import model.Room;
import model.misc.Direction;
import model.misc.Vec2;

public class Door extends Entity {
    public final Direction side;
    public final double offset;
    private Door destination;

    public Door(Room room, Direction side, double offset) {
        super(room, getPosition(room, side, offset), new Vec2(1, 1));
        this.side = side;
        this.offset = offset;
    }

    public Vec2 getPosition() {
        return getPosition(getRoom(), side, offset);
    }

    public static Vec2 getPosition(Room room, Direction side, double offset) {
        switch (side) {
            case UP:
                return new Vec2(offset, 0);
            case DOWN:
                return new Vec2(offset, room.size.y - 1);
            case LEFT:
                return new Vec2(0, offset);
            case RIGHT:
                return new Vec2(room.size.x - 1, offset);
            default:
                return Vec2.ZERO;
        }
    }

    public Vec2 getFacingPosition() {
        return getPosition().subtract(side.getUnitVec2());
    }

    public Door getDestination() {
        return destination;
    }

    public void setDestination(Door destination) {
        this.destination = destination;
    }

    @Override
    public boolean isUsable() {
        return true;
    }

    @Override
    public void use(LivingEntity user) {
        user.setPosition(getFacingPosition());
    }

    public static Door random(Random random, Room room) {
        return new Door(room, Direction.random(random), random.nextDouble());
    }
}
