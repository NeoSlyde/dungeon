package model.entities.monsters;

import model.Room;
import model.misc.Vec2;
import view.DrawableVisitor;

public class Ghost extends Monster {
    public Ghost(Room room, Vec2 position) {
        super(room, position);
    }

    @Override
    public double getStrength() {
        return 5;
    }

    @Override
    public double getSpeed() {
        return 1;
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }

    @Override
    public String getName() {
        return "Ghost";
    }

}
