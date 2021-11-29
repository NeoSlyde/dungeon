package model.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import model.Room;
import model.entities.Entity;
import model.entities.EntityFactory;
import model.entities.Player;
import model.entities.Wall;
import model.misc.Vec2;

public class RandomWorldFactory implements WorldFactory {
    private final Random random;

    public RandomWorldFactory(Random random) {
        this.random = random;
    }

    public World generate() {
        int roomCount = new Random().nextInt(6) + 10;
        List<Room> rooms = new ArrayList<>();

        while (rooms.size() < roomCount) {
            Room room = new Room();
            Entity[][] grid = new Entity[(int) Room.defaultSize.y][(int) Room.defaultSize.x];

            fillWithEntity(grid, (r, p) -> new Wall(room, p));
            int mainPathWidth = random.nextInt(4) + 1;
            clearPath(grid, new Vec2(31, 5), new Vec2(0, 0), mainPathWidth);
            int subroomCount = random.nextInt(4) + 1;
            for (int k = 0; k < subroomCount; k++) {
                cutSubroom(grid);
            }

            Stream.of(grid).flatMap(Stream::of).filter(Objects::nonNull).forEach(room::addEntity);
            rooms.add(room);
        }
        return new World(rooms, new Player(rooms.get(0), new Vec2(1, 1)));
    }

    private void fillWithEntity(Entity[][] grid, EntityFactory factory) {
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[j].length; i++) {
                set(grid, i, j, factory.generate(null, new Vec2(i, j)));
            }
        }
    }

    private void clearPath(Entity[][] grid, Vec2 start, Vec2 end, int radius) {
        int startY = (int) Math.min(start.y, end.y);
        int endY = (int) Math.max(start.y, end.y);
        int startX = (int) Math.min(start.x, end.x);
        int endX = (int) Math.max(start.x, end.x);
        for (int j = startY; j <= endY; j++) {
            for (int i = -radius; i <= radius; i++) {
                remove(grid, i + (int) start.x, j);
            }
        }
        for (int i = startX; i <= endX; i++) {
            for (int j = -radius; j <= radius; j++) {
                remove(grid, i, j + (int) end.y);
            }
        }
    }

    private void cutSubroom(Entity[][] grid) {
        Vec2 size = new Vec2(4 + random.nextInt(4), 4 + random.nextInt(4));
        // Find the best position to put the subroom
        Vec2 bestPos = null;
        int bestCount = -1;
        for (int i = 0; i < 10_000; ++i) {
            Vec2 pos = new Vec2(1 + random.nextInt((int) (Room.defaultSize.x - size.x - 2)),
                    1 + random.nextInt((int) (Room.defaultSize.y - size.y - 2)));
            int count = 0;
            for (int j = -1; j < size.y + 1; j++) {
                for (int i1 = -1; i1 < size.x + 1; i1++) {
                    count += grid[j + (int) pos.y][i1 + (int) pos.x] != null ? 1 : 0;
                }
            }
            if (count >= bestCount) {
                bestCount = count;
                bestPos = pos;
            }
        }
        // Clear the area
        for (int i = 0; i < size.x; ++i) {
            for (int j = 0; j < size.y; ++j) {
                remove(grid, (int) bestPos.x + i, (int) bestPos.y + j);
            }
        }

        // Cut out a path to enter the subroom
        Vec2 entrance = null;
        for (int radius = 1; entrance == null && radius < 30; ++radius) {
            for (int i = Math.max(0, (int) bestPos.x - radius); entrance == null
                    && i < Math.min(grid.length, bestPos.y + size.y + radius); ++i) {
                if (bestPos.x - radius >= 0 && grid[i][(int) bestPos.x - radius] == null)
                    entrance = new Vec2(bestPos.x - radius, i);
                if (bestPos.x + size.x + radius < grid[i].length
                        && grid[i][(int) (bestPos.x + size.x + radius)] == null)
                    entrance = new Vec2(bestPos.x + size.x + radius, i);
            }
        }
        if (entrance != null) {
            clearPath(grid, new Vec2(entrance.x, bestPos.y + size.y / 2), entrance, 0);
            clearPath(grid, new Vec2(bestPos.x + size.x / 2, bestPos.y + size.y / 2),
                    new Vec2(entrance.x, bestPos.y + size.y / 2), 0);
        }
    }

    private void remove(Entity[][] grid, int x, int y) {
        if (x >= 0 && y >= 0 && x < grid[0].length && y < grid.length) {
            if (grid[y][x] != null) {
                grid[y][x].getRoom().removeEntity(grid[y][x]);
                grid[y][x] = null;
            }
        }
    }

    private void set(Entity[][] grid, int x, int y, Entity entity) {
        if (x >= 0 && y >= 0 && x < grid[0].length && y < grid.length) {
            if (grid[y][x] != null) {
                grid[y][x].getRoom().removeEntity(grid[y][x]);
            }
            grid[y][x] = entity;
        }
    }
}
