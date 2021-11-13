package model.generator;

import java.util.ArrayList;
import java.util.List;

import model.World;
import model.entities.Entity;
import model.entities.Wall;
import model.misc.Position;
import model.misc.Room;

public class RandomWorldGenerator implements WorldGenerator {
  @Override
  public World generate() {
    World w = new World();
    for (int i = 0; i < 16; i++) {
      Room r = new Room(i);
      generateRoom(w, r).forEach(w::addEntity);
    }
    return w;
  }

  private List<Entity> generateRoom(World w, Room r) {
    var xs = new ArrayList<Entity>();
    roomBorderPattern(r).forEach(xs::add);
    return xs;
  }

  private List<Entity> roomBorderPattern(Room room) {
    var xs = new ArrayList<Entity>();
    for (int i = 0; i < room.getSize().width; i++) {
      xs.add(new Wall(new Position(i, 0, room)));
      xs.add(new Wall(new Position(i, room.getSize().height - 1, room)));
    }
    for (int i = 1; i < room.getSize().height - 1; i++) {
      xs.add(new Wall(new Position(0, i, room)));
      xs.add(new Wall(new Position(room.getSize().width - 1, i, room)));
    }
    return xs;
  }

  private List<Entity> pattern1(Room room) {
    var xs = new ArrayList<Entity>();
    xs.add(new Wall(new Position(0, 0, room)));
    xs.add(new Wall(new Position(1, 0, room)));
    xs.add(new Wall(new Position(2, 0, room)));
    xs.add(new Wall(new Position(0, 1, room)));
    return xs;
  }
}
