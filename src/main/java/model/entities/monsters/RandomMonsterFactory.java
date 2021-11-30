package model.entities.monsters;

import java.util.Random;

import model.Room;
import model.entities.EntityFactory;
import model.misc.Vec2;

public class RandomMonsterFactory implements EntityFactory {
    private final Random random;

    public RandomMonsterFactory(Random random) {
        this.random = random;
    }

    @Override
    public Monster generate(Room room, Vec2 position) {
        final int randInt = random.nextInt(4);
        switch (randInt) {
            case 0:
                return new Ghost(room, position);
            case 1:
                return new Goblin(room, position);
            case 2:
                return new Skeleton(room, position);
        }
        return new Skeleton(room, position);
    }

}
