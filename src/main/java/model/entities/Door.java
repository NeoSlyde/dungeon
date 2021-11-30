package model.entities;

import java.util.Random;

import audio.AudioDataFactory;
import audio.AudioPlayer;
import model.Room;
import model.misc.Direction;
import model.misc.Vec2;
import view.DrawableVisitor;

public class Door extends Entity {
    public final Direction side;
    public final double pos;
    private Door destination;
    private AudioPlayer audioPlayer;
    private AudioDataFactory audioDataFactory;

    public Door(Room room, Direction side, double pos, AudioDataFactory audioDataFactory, AudioPlayer audioPlayer) {
        super(room, getPosition(room, side, pos), new Vec2(1, 1));
        this.side = side;
        this.pos = pos;
        this.audioDataFactory = audioDataFactory;
        this.audioPlayer = audioPlayer;
    }

    public Vec2 getPosition() {
        return getPosition(getRoom(), side, pos);
    }

    public static Vec2 getPosition(Room room, Direction side, double pos) {
        switch (side) {
            case UP:
                return new Vec2(pos, 0);
            case DOWN:
                return new Vec2(pos, room.size.y - 1);
            case LEFT:
                return new Vec2(0, pos);
            case RIGHT:
                return new Vec2(room.size.x - 1, pos);
            default:
                return Vec2.ZERO;
        }
    }

    public Vec2 getFacingPosition() {
        return getPosition().subtract(side.getUnitVec2());
    }

    public Door getDestination() {
        return destination;
    }

    public void setDestination(Door destination) {
        this.destination = destination;
    }

    @Override
    public boolean isUsable() {
        return true;
    }

    @Override
    public void use(LivingEntity user) {
        audioPlayer.play(audioDataFactory.openDoorSoundEffect());
        user.setPosition(destination.getFacingPosition());
        user.setRoom(destination.getRoom());
    }

    public static Door random(Random random, Room room, AudioPlayer audioPlayer, AudioDataFactory audioDataFactory) {
        return new Door(room, Direction.random(random), random.nextDouble(), audioDataFactory, audioPlayer);
    }

    @Override
    public void draw(DrawableVisitor visitor) {
        visitor.draw(this);
    }
}
