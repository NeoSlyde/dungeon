package model.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.World;
import model.entities.Door;
import model.entities.Entity;
import model.entities.RoomWall;
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
    Random start = new Random();
    roomBorderPattern(r).forEach(xs::add);
    subRoom1(r, start.nextInt((int) r.getSize().height - 2) + 2).forEach(xs::add);
    subRoom2(r, start.nextInt((int) r.getSize().height - 2) + 2).forEach(xs::add);
    return xs;
  }

  private List<Entity> roomBorderPattern(Room room) {
    var xs = new ArrayList<Entity>();
    Random r = new Random();
    int door1 = r.nextInt((int) room.getSize().height - 2) + 1;
    int door2 = r.nextInt((int) room.getSize().height - 2) + 1;
    for (int i = 0; i < room.getSize().width; i++) {
      xs.add(new Wall(new Position(i, 0, room)));
      xs.add(new Wall(new Position(i, room.getSize().height - 1, room)));
    }
    for (int i = 1; i < room.getSize().height - 1; i++) {
      if (i == door1) {
        xs.add(new Door(new Position(0, i, room), new Position(1, 1, new Room(room.id - 1))));
      } else {
        xs.add(new Wall(new Position(0, i, room)));
      }
      if (i == door2) {
        xs.add(new Door(new Position(room.getSize().width - 1, i, room), new Position(1, 1, new Room(room.id + 1))));
      } else {
        xs.add(new Wall(new Position(room.getSize().width - 1, i, room)));
      }
    }
    return xs;
  }

  private List<Entity> subRoom1(Room room, int startPosition) {
    var xs = new ArrayList<Entity>();
    Random r = new Random();
    int entrancePos = r.nextInt(7) + 1;
    for (int i = 1; i < 7; i++) {
      xs.add(new RoomWall(new Position(startPosition, i, room)));
    }
    for (int i = 0; i < 9; i++) {
      if (i != entrancePos) {
        xs.add(new RoomWall(new Position(startPosition + i, 6, room)));
      }
    }
    for (int i = 1; i < 7; i++) {
      xs.add(new RoomWall(new Position(startPosition + 8, i, room)));
    }
    return xs;
  }

  private List<Entity> subRoom2(Room room, int startPosition) {
    var xs = new ArrayList<Entity>();
    Random r = new Random();
    int entrancePos = r.nextInt(7) + 1;
    for (int i = 10; i < 17; i++) {
      xs.add(new RoomWall(new Position(startPosition, i, room)));
    }
    for (int i = 0; i < 13; i++) {
      if (i != entrancePos) {
        xs.add(new RoomWall(new Position(startPosition + i, 10, room)));
      }
    }
    for (int i = 10; i < 17; i++) {
      xs.add(new RoomWall(new Position(startPosition + 12, i, room)));
    }
    return xs;
  }
}
