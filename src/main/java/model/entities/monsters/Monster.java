package model.entities.monsters;

import model.Room;
import model.entities.LivingEntity;
import model.misc.Vec2;
import view.DrawableVisitor;

public abstract class Monster extends LivingEntity {
    protected double health = getMaxHealth();

    public Monster(Room room, Vec2 position) {
        super(room, position, new Vec2(0.5, 0.5));
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }

}
