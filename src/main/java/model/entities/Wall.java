package model.entities;

import model.Room;
import model.misc.Vec2;
import view.DrawableVisitor;

public class Wall extends Entity {
    public Wall(Room room, Vec2 position) {
        super(room, position, new Vec2(1, 1));
    }

    @Override
    public boolean isUsable() {
        return false;
    }

    @Override
    public void use(LivingEntity user) {
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }
}
