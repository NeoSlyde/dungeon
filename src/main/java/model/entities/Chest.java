package model.entities;

import audio.AudioDataFactory;
import audio.AudioPlayer;
import model.Inventory;
import model.Room;
import model.misc.Vec2;
import view.DrawableVisitor;

public class Chest extends Entity {
    private Inventory inventory = new Inventory();
    private AudioDataFactory audioDataFactory;
    private AudioPlayer audioPlayer;

    public Chest(Room room, Vec2 position, AudioDataFactory audioDataFactory, AudioPlayer audioPlayer) {
        super(room, position, new Vec2(1, 1));
        this.audioDataFactory = audioDataFactory;
        this.audioPlayer = audioPlayer;
    }

    @Override
    public boolean isUsable() {
        return true;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void use(LivingEntity user) {
        user.getInventory().addAll(inventory);
        getRoom().removeEntity(this);
        audioPlayer.play(audioDataFactory.chestSFX());
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }
}
