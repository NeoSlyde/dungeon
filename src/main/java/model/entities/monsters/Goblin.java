package model.entities.monsters;

import model.Room;
import model.misc.Vec2;
import view.DrawableVisitor;

public class Goblin extends Monster {

    public Goblin(Room room, Vec2 position) {
        super(room, position);
    }

    @Override
    public double getStrength() {
        return 10;
    }

    @Override
    public double getSpeed() {
        return 2;
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }

    @Override
    public String getName() {
        return "Goblin";
    }

}
