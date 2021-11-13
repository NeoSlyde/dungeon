package model.generator;

import java.util.ArrayList;
import java.util.List;

import model.World;
import model.entities.Entity;
import model.entities.Wall;
import model.misc.Position;
import model.misc.Room;
import model.misc.Size;

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
    generatePattern(w, r).forEach(xs::add);
    return xs;
  }

  private List<Entity> generatePattern(World world, Room room) {
    var xs = new ArrayList<Entity>();
    xs.add(new Wall(new Position(0, 0, room), new Size(1, 1)));
    xs.add(new Wall(new Position(1, 0, room), new Size(1, 1)));
    xs.add(new Wall(new Position(2, 0, room), new Size(1, 1)));
    xs.add(new Wall(new Position(0, 1, room), new Size(1, 1)));
    return xs;
  }
}
