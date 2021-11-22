package model.misc;

public class Vec2 {
    public final double x, y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2(Vec2 p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Vec2 add(Vec2 p) {
        return new Vec2(x + p.x, y + p.y);
    }

    public Vec2 subtract(Vec2 p) {
        return new Vec2(x - p.x, y - p.y);
    }
}
