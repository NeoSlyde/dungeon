package model.misc;

public enum Direction {
  NORTH, EAST, SOUTH, WEST;

  /**
   * @return the x component of the unit vector in the direction
   */
  public double unitX() {
    return this == EAST ? 1 : this == WEST ? -1 : 0;
  }

  /**
   * @return the y component of the unit vector in the direction
   */
  public double unitY() {
    return this == NORTH ? -1 : this == SOUTH ? 1 : 0;
  }

  public Direction inverse() {
    return this == NORTH ? SOUTH : this == SOUTH ? NORTH : this == EAST ? WEST : EAST;
  }
}
