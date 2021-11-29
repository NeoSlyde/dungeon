package view.sprites;

import model.misc.Direction;
import view.DrawableVisitor;

public class DirectedAnimatedSprite {
    private AnimatedSprite upSprite;
    private AnimatedSprite rightSprite;
    private AnimatedSprite downSprite;
    private AnimatedSprite leftSprite;

    public DirectedAnimatedSprite(AnimatedSprite upSprite, AnimatedSprite rightSprite,
            AnimatedSprite downSprite, AnimatedSprite leftSprite) {
        this.upSprite = upSprite;
        this.rightSprite = rightSprite;
        this.downSprite = downSprite;
        this.leftSprite = leftSprite;
    }

    public void draw(DrawableVisitor d, double t, Direction direction) {
        getSprite(direction).draw(d, t);
    }

    private AnimatedSprite getSprite(Direction direction) {
        switch (direction) {
            case UP:
                return upSprite;
            case RIGHT:
                return rightSprite;
            case DOWN:
                return downSprite;
            case LEFT:
                return leftSprite;
            default:
                return null;
        }
    }
}