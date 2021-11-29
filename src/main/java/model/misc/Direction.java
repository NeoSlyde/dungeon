package model.misc;

import java.util.Random;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Vec2 getUnitVec2() {
        switch (this) {
            case UP:
                return new Vec2(0, -1);
            case DOWN:
                return new Vec2(0, 1);
            case LEFT:
                return new Vec2(-1, 0);
            case RIGHT:
                return new Vec2(1, 0);
            default:
                return new Vec2(0, 0);
        }
    }

    public Direction getOpposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return null;
        }
    }

    public static Direction random(Random random) {
        return values()[random.nextInt(values().length)];
    }
}
