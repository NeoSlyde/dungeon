package model.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import audio.AudioDataFactory;
import audio.AudioPlayer;
import model.Room;
import model.entities.Door;
import model.entities.Entity;
import model.entities.EntityFactory;
import model.entities.Player;
import model.entities.RandomChestFactory;
import model.entities.Wall;
import model.misc.Direction;
import model.misc.Vec2;

public class RandomWorldFactory implements WorldFactory {
    private final Random random;
    private final EntityFactory monsterFactory;
    private AudioPlayer audioPlayer;
    private AudioDataFactory audioDataFactory;

    public RandomWorldFactory(Random random, EntityFactory monsterFactory, AudioPlayer audioPlayer,
            AudioDataFactory audioDataFactory) {
        this.random = random;
        this.monsterFactory = monsterFactory;
        this.audioDataFactory = audioDataFactory;
        this.audioPlayer = audioPlayer;
    }

    public World generate() {
        int roomCount = new Random().nextInt(6) + 10;
        List<Room> rooms = new ArrayList<>();

        Door prevDoor = null;
        while (rooms.size() < roomCount) {
            Room room = new Room();
            EntityFactory chestFactory = (_r, pos) -> new RandomChestFactory().generate(room, pos);
            Door backDoor = null;
            if (prevDoor != null) {
                backDoor = new Door(room, prevDoor.side.getOpposite(), prevDoor.pos, audioDataFactory, audioPlayer);
                prevDoor.setDestination(backDoor);
                backDoor.setDestination(prevDoor);
            }
            Door nextDoor = generateNextDoor(backDoor, room);

            Entity[][] grid = new Entity[(int) Room.defaultSize.y][(int) Room.defaultSize.x];

            fillWithEntity(grid, (r, p) -> new Wall(room, p));

            int mainPathWidth = random.nextInt(4) + 1;
            Vec2 startPos = prevDoor == null ? room.size.multiply(0.5) : prevDoor.getDestination().getFacingPosition();
            clearPath(grid, startPos, nextDoor.getFacingPosition(), mainPathWidth);

            int subroomCount = random.nextInt(4) + 1;
            for (int k = 0; k < subroomCount; k++)
                cutSubroom(grid, chestFactory);

            fillBordersWithEntity(grid, (r, p) -> new Wall(room, p));

            if (prevDoor != null)
                set(grid, (int) backDoor.getPosition().x, (int) backDoor.getPosition().y, backDoor);
            set(grid, (int) nextDoor.getPosition().x, (int) nextDoor.getPosition().y, nextDoor);

            generateMonsters(grid, room);

            rooms.add(room);
            prevDoor = nextDoor;
        }
        return new World(rooms, new Player(rooms.get(0), Room.defaultSize.multiply(0.5)));
    }

    private void fillWithEntity(Entity[][] grid, EntityFactory factory) {
        for (int j = 0; j < grid.length; j++) {
            for (int i = 0; i < grid[j].length; i++) {
                set(grid, i, j, factory.generate(null, new Vec2(i, j)));
            }
        }
    }

    private void fillBordersWithEntity(Entity[][] grid, EntityFactory factory) {
        for (int j = 0; j < grid.length; j++) {
            set(grid, grid[0].length - 1, j, factory.generate(null, new Vec2(grid[0].length - 1, j)));
            set(grid, 0, j, factory.generate(null, new Vec2(0, j)));
        }
        for (int i = 0; i < grid[0].length; i++) {
            set(grid, i, grid.length - 1, factory.generate(null, new Vec2(i, grid.length - 1)));
            set(grid, i, 0, factory.generate(null, new Vec2(i, 0)));
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

    private void cutSubroom(Entity[][] grid, EntityFactory chestFactory) {
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

        // Generate a chest
        Vec2 chestPos = new Vec2(bestPos.x + size.x / 2, bestPos.y + size.y / 2);
        set(grid, (int) chestPos.x, (int) chestPos.y, chestFactory.generate(null, chestPos));
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

    private Door generateNextDoor(Door backDoor, Room room) {
        Direction prevSide = backDoor == null ? null : backDoor.side;
        Direction side;
        while ((side = Direction.random(random)).equals(prevSide)) {
        }
        double pos;
        if (side == Direction.LEFT || side == Direction.RIGHT) {
            pos = random.nextInt((int) room.size.y - 2) + 1;
        } else {
            pos = random.nextInt((int) room.size.x - 2) + 1;
        }
        return new Door(room, side, pos, audioDataFactory, audioPlayer);
    }

    private void generateMonsters(Entity[][] grid, Room room) {
        int monsterCount = random.nextInt(4) + 1;
        for (int i = 0; i < monsterCount; ++i) {
            Vec2 pos = null;
            for (int j = 0; j < 10_000; ++j) {
                Vec2 randpos = new Vec2(random.nextInt((int) room.size.x), random.nextInt((int) room.size.y));
                if (grid[(int) randpos.y][(int) randpos.x] == null) {
                    pos = randpos;
                    break;
                }
            }
            if (pos == null)
                continue;
            set(grid, (int) pos.x, (int) pos.y, monsterFactory.generate(room, pos));
        }
    }
}
