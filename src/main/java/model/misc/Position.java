package model.misc;

public class Position {
  public final double x, y;
  public final Room room;

  public Position(double x, double y, Room room) {
    this.x = x;
    this.y = y;
    this.room = room;
  }

  public double distance(Position other) {
    return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
  }

  public Position addX(double x) {
    return new Position(this.x + x, y, room);
  }

  public Position addY(double y) {
    return new Position(x, this.y + y, room);
  }
}
