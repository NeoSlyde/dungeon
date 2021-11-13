package model.misc;

public class Room {
  public final int id;

  public Room(int id) {
    this.id = id;
  }

  public Size getSize() {
    return new Size(32, 18);
  }

  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (o == this)
      return true;
    if (!(o instanceof Room))
      return false;

    Room other = (Room) o;
    return this.id == other.id;
  }
}
