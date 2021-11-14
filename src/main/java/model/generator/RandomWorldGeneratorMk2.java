package model.generator;

import java.util.Random;
import java.util.stream.IntStream;

import model.World;
import model.entities.Door;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Room;

public class RandomWorldGeneratorMk2 implements WorldGenerator {
  @Override
  public World generate() {
    World w = new World();
    var random = new Random();
    int roomCount = random.nextInt(7) + 10;

    Room[] rooms = IntStream.range(0, roomCount).mapToObj(i -> new Room(i)).toArray(Room[]::new);
    int[] door_positions = new int[roomCount - 1];
    Direction[] door_sides = new Direction[roomCount - 1];
    for (int i = 0; i < roomCount - 1; i++) {
      Direction d = randomDirection(random);
      door_sides[i] = d;
      if (Math.abs(d.unitX()) != 0) {
        door_positions[i] = random.nextInt((int) (Room.getSize().width) - 2) + 1;
      } else {
        door_positions[i] = random.nextInt((int) (Room.getSize().height) - 2) + 1;
      }
    }

    for (int i = 0; i < roomCount - 1; i++) {
      int pos = door_positions[i];
      Direction d = door_sides[i];
      Room room1 = rooms[i], room2 = rooms[i + 1];
      Door door = null;
      if (d == Direction.EAST)
        door = new Door(new Position(Room.getSize().width - 1, pos, room1), new Position(0, pos, room2));
      else if (d == Direction.WEST)
        door = new Door(new Position(0, pos, room1), new Position(Room.getSize().width - 1, pos, room2));
      else if (d == Direction.NORTH)
        door = new Door(new Position(pos, 0, room1), new Position(pos, Room.getSize().height - 1, room2));
      else if (d == Direction.SOUTH)
        door = new Door(new Position(pos, Room.getSize().height - 1, room1), new Position(pos, 0, room2));
      w.addEntity(door);
      w.addEntity(door.inverse());
    }
    return w;
  }

  private static Direction randomDirection(Random random) {
    int i = random.nextInt(2);
    switch (i) {
    case 0:
      return Direction.NORTH;
    case 1:
      return Direction.EAST;
    default:
      return Direction.NORTH;
    }
  }
}
