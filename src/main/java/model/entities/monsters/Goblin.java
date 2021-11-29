package model.entities.monsters;

import model.Room;
import model.misc.Vec2;
import view.DrawableVisitor;

public class Goblin extends Monster {

    public Goblin(Room room, Vec2 position) {
        super(room, position);
    }

    @Override
    public double getMaxHealth() {
        return 75;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public double getStrength() {
        return 10;
    }

    @Override
    public double getSpeed() {
        return 6;
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }

}
