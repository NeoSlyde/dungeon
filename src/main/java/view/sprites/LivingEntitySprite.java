package view.sprites;

import model.entities.LivingEntity;
import view.DrawableVisitor;

public class LivingEntitySprite {
    private DirectedAnimatedSprite idle;
    private DirectedAnimatedSprite walk;
    private DirectedAnimatedSprite running;

    public LivingEntitySprite(
            DirectedAnimatedSprite idle,
            DirectedAnimatedSprite walk,
            DirectedAnimatedSprite moving) {
        this.idle = idle;
        this.walk = walk;
        this.running = moving;
    }

    public void draw(DrawableVisitor d, LivingEntity e) {
        double t = e.getRoom().getWorld().getT();
        if (e.isRunning() && e.isMoving()) {
            running.draw(d, t, e.getFacingDirection());
        } else if (e.isMoving()) {
            walk.draw(d, t, e.getFacingDirection());
        } else {
            idle.draw(d, t, e.getFacingDirection());
        }
    }
}
