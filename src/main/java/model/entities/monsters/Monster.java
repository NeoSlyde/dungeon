package model.entities.monsters;

import model.Room;
import model.entities.LivingEntity;
import model.misc.Direction;
import model.misc.Vec2;
import view.DrawableVisitor;

public abstract class Monster extends LivingEntity {
    public Monster(Room room, Vec2 position) {
        super(room, position, new Vec2(0.5, 0.5));
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }

    private double lastDirChangeT = 0;

    public void update(double dt) {
        var player = getRoom().getWorld().getPlayer();
        if (player.getPosition().distance(getPosition()) > 6) {
            setMoving(false);
            return;
        }
        if ((getRoom().getWorld().getT() - lastDirChangeT) > 0.5) {
            lastDirChangeT = getRoom().getWorld().getT();
            var vec = player.getPosition().subtract(getPosition());
            setFacingDirection(Math.abs(vec.x) > Math.abs(vec.y) ? vec.x > 0 ? Direction.RIGHT : Direction.LEFT
                    : vec.y > 0 ? Direction.DOWN : Direction.UP);
        }
        setMoving(player.getPosition().distance(getPosition()) >= 1);
        super.update(dt);
    }
}
