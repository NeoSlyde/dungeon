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

    public RandomChestFactory(Random random, AudioDataFactory audioDataFactory, AudioPlayer audioPlayer) {
        this.random = random;
        this.audioDataFactory = audioDataFactory;
        this.audioPlayer = audioPlayer;
    }

    @Override
    public Chest generate(Room room, Vec2 position) {
        var chest = new Chest(room, position, audioDataFactory, audioPlayer);
        chest.getInventory().addItem(new HealingPotion(), random.nextInt(9));
        chest.getInventory().addItem(new ManaPotion(), random.nextInt(9));
        return chest;
    }
}
