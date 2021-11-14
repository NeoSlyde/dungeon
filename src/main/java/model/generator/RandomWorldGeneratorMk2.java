package model.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import model.World;
import model.entities.Chest;
import model.entities.Entity;
import model.entities.Wall;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Room;

public class RandomWorldGeneratorMk2 implements WorldGenerator {
  private Random random = new Random();

  @Override
  public World generate() {
    Room[] rooms = IntStream.range(0, random.nextInt(6) + 10).mapToObj(Room::new).toArray(Room[]::new);
    Position spawnPoint = new Position(random.nextInt((int) Room.getSize().width / 2),
        random.nextInt((int) Room.getSize().height / 2), rooms[0]);

    World w = new World(spawnPoint);

    var layout = generateRoomLayout(spawnPoint, new Position(0, 16, null), new Room(0));
    layoutToRoom(layout).forEach(w::addEntity);

    return w;
  }

  private Entity[][] generateRoomLayout(Position start, Position end, Room room) {
    int h = (int) Room.getSize().height, w = (int) Room.getSize().width;
    var layout = new Entity[h][w];
    for (int i = 0; i < h; i++)
      for (int j = 0; j < w; j++)
        layout[i][j] = new Wall(new Position(j, i, room));

    generatePathInLayout(start, end, room, 1, 3, layout);
    int subRoomCount = random.nextInt(4) + 1;
    for (int i = 0; i < subRoomCount; i++)
      tryCutOutSubroom(layout, room);

    for (int i = 0; i < h; i++) {
      layout[i][0] = new Wall(new Position(0, i, room));
      layout[i][w - 1] = new Wall(new Position(w - 1, i, room));
    }
    for (int i = 0; i < w; i++) {
      layout[0][i] = new Wall(new Position(i, 0, room));
      layout[h - 1][i] = new Wall(new Position(i, h - 1, room));
    }

    return layout;
  }

  private void generatePathInLayout(Position start, Position end, Room room, int pathStep, int rand,
      Entity[][] layout) {
    int h = (int) Room.getSize().height, w = (int) Room.getSize().width;

    Position last = start;
    while (last.distance(end) > pathStep) {
      double distX = end.x - last.x;
      double distY = end.y - last.y;
      double dist = Math.sqrt(distX * distX + distY * distY);

      double dx = pathStep * distX / dist;
      double dy = pathStep * distY / dist;

      last = new Position(last.x + dx, last.y + dy, null);

      int stepH = pathStep + (rand > 0 ? random.nextInt(rand) : 0);
      int stepW = pathStep + (rand > 0 ? random.nextInt(rand) : 0);
      for (int i = Math.max(0, (int) last.y - stepH); i < Math.min(h, (int) last.y + stepH); ++i)
        for (int j = Math.max(0, (int) last.x - stepW); j < Math.min(w, (int) last.x + stepW); ++j)
          layout[i][j] = null;
    }
  }

  private void tryCutOutSubroom(Entity[][] layout, Room room) {
    // Find the best place to remove walls
    int w = random.nextInt(6) + 2;
    int h = random.nextInt(6) + 2;
    int bestX = -1, bestY = -1;
    int biggestWallCount = -1;
    for (int i = 0; i < 1000; ++i) {
      int x = random.nextInt(layout[0].length - w);
      int y = random.nextInt(layout.length - h);
      int wallCount = 0;
      for (int j = Math.max(0, y - 1); j < Math.min(layout.length, y + h + 1); ++j)
        for (int k = Math.max(0, x - 1); k < Math.min(layout[0].length, x + w + 1); ++k)
          wallCount += layout[j][k] != null ? 1 : 0;
      if (wallCount > biggestWallCount) {
        biggestWallCount = wallCount;
        bestX = x;
        bestY = y;
      }
    }

    // Remove all the walls in the chosen area
    for (int i = bestY; i < bestY + h; ++i)
      for (int j = bestX; j < bestX + w; ++j)
        layout[i][j] = null;

    Position entrance = null;
    for (int radius = 1; entrance == null && radius < 10; ++radius) {
      for (int i = Math.max(0, bestY - radius); entrance == null
          && i < Math.min(layout.length, bestY + h + radius); ++i) {
        if (bestX - radius >= 0 && layout[i][bestX - radius] == null)
          entrance = new Position(bestX - radius, i, room);
        if (bestX + w + radius < layout[i].length && layout[i][bestX + w + radius] == null)
          entrance = new Position(bestX + w + radius, i, room);
      }
    }
    if (entrance != null) {
      generatePathInLayout(new Position(entrance.x, bestY, room), entrance, room, 1, 0, layout);
      generatePathInLayout(new Position(bestX, bestY, room), new Position(entrance.x, bestY, room), room, 1, 0, layout);
    }
    // Generate a chest
    Direction chestOrientation = Direction.values()[random.nextInt(Direction.values().length)];
    int chestX = bestX + (chestOrientation == Direction.EAST ? w - 1 : chestOrientation == Direction.WEST ? 0 : w / 2);
    int chestY = bestY
        + (chestOrientation == Direction.SOUTH ? h - 1 : chestOrientation == Direction.NORTH ? 0 : h / 2);
    layout[chestY][chestX] = new Chest(new Position(chestX, chestY, room));
  }

  private List<Entity> layoutToRoom(Entity[][] layout) {
    var entities = new ArrayList<Entity>();
    for (int i = 0; i < layout.length; i++)
      for (int j = 0; j < layout[i].length; j++)
        if (layout[i][j] != null)
          entities.add(layout[i][j]);
    return entities;
  }
}
