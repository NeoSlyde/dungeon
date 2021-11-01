package model.misc;

public class Position {
  public final double x, y;
  public final Room room;

  public Position(double x, double y, Room room) {
    this.x = x;
    this.y = y;
    this.room = room;
  }
}
