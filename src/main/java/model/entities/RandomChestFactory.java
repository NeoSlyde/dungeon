package model.entities;

import java.util.Random;

import audio.AudioDataFactory;
import audio.AudioPlayer;
import model.Room;
import model.items.HealingPotion;
import model.items.ManaPotion;
import model.misc.Vec2;

public class RandomChestFactory implements EntityFactory {
    private final Random random;
    private final AudioPlayer audioPlayer;
    private final AudioDataFactory audioDataFactory;
    private final EntityFactory monsterFactory;

    public RandomChestFactory(Random random, AudioDataFactory audioDataFactory, AudioPlayer audioPlayer,
            EntityFactory monsterFactory) {
        this.random = random;
        this.audioDataFactory = audioDataFactory;
        this.audioPlayer = audioPlayer;
        this.monsterFactory = monsterFactory;
    }

    @Override
    public Chest generate(Room room, Vec2 position) {
        Chest chest;
        if (random.nextInt(10) == 0) {
            chest = new TrappedChest(room, position, audioDataFactory, audioPlayer, monsterFactory);
        } else {
            chest = new Chest(room, position, audioDataFactory, audioPlayer);
        }
        chest.getInventory().addItem(new HealingPotion(), random.nextInt(9));
        chest.getInventory().addItem(new ManaPotion(), random.nextInt(9));
        return chest;
    }
}
