package model.entities;

import model.Room;
import model.items.HealingPotion;
import model.misc.Vec2;
import view.DrawableVisitor;

public class Player extends LivingEntity {
    public Player(Room room, Vec2 position) {
        super(room, position, new Vec2(0.5, 0.5));
    }

    private double health = getMaxHealth();

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
        return isRunning() ? 8 : 4;
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }
}
