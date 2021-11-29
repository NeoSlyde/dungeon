package view.sprites;

import java.util.stream.Stream;

import view.DrawableVisitor;

public class AnimatedSprite {
    private Sprite[] sprites;
    private double speed;

    public AnimatedSprite(double speed, Sprite... sprites) {
        this.sprites = sprites;
        this.speed = speed;
    }

    public AnimatedSprite(double speed, String... sprites) {
        this.sprites = Stream.of(sprites).map(Sprite::new).toArray(Sprite[]::new);
        this.speed = speed;
    }

    public void draw(DrawableVisitor d, double t) {
        if (sprites.length > 0) {
            var s = sprites[(int) (t * speed) % sprites.length];
            s.draw(d);
        }
    }
}
