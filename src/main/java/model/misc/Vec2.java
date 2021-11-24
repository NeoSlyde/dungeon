package model.misc;

public class Vec2 {
    public static final Vec2 ZERO = new Vec2(0, 0);

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

    public Vec2 multiply(double s) {
        return new Vec2(x * s, y * s);
    }

    public Vec2 multiply(Vec2 p) {
        return new Vec2(x * p.x, y * p.y);
    }

    public Vec2 divide(Vec2 p) {
        return new Vec2(x / p.x, y / p.y);
    }

    public Vec2 withX(double newX) {
        return new Vec2(newX, y);
    }

    public Vec2 withY(double newY) {
        return new Vec2(x, newY);
    }

    // Returns the same vector with the given magnitude.
    // If the vector has length 0, returns the same vector.
    public Vec2 withMagnitude(double magnitude) {
        double len = length();
        return len == 0 ? this : multiply(magnitude / len);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Vec2 vec2 = (Vec2) o;

        return vec2.x == x && vec2.y == y;
    }
}
