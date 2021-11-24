package model.entities;

import model.Room;
import model.misc.Vec2;

public class Player extends LivingEntity {
    public Player(Room room, Vec2 position) {
        super(room, position, new Vec2(1, 1));
    }

    private double health = getMaxHealth();
    private boolean running = false;

    @Override
    public double getMaxHealth() {
        return 100;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public double getStrength() {
        return 20;
    }

    @Override
    public double getSpeed() {
        return running ? 8 : 4;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
