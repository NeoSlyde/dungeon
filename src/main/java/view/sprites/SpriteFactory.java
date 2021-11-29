package view.sprites;

public class SpriteFactory {
    public static final SpriteFactory INSTANCE = new SpriteFactory();

    public LivingEntitySprite playerSprite() {
        return cachedPlayerSprite;
    }

    private LivingEntitySprite cachedPlayerSprite = loadPlayerSprite();

    private LivingEntitySprite loadPlayerSprite() {
        var walkingSpeed = 4;
        var runningSpeed = 8;
        var idle = new DirectedAnimatedSprite(
                new AnimatedSprite(0, "/player/up0.png"),
                new AnimatedSprite(0, "/player/right0.png"),
                new AnimatedSprite(0, "/player/down0.png"),
                new AnimatedSprite(0, "/player/left0.png"));
        var walking = new DirectedAnimatedSprite(
                new AnimatedSprite(walkingSpeed, "/player/up1.png", "/player/up2.png"),
                new AnimatedSprite(walkingSpeed, "/player/right1.png", "/player/right2.png"),
                new AnimatedSprite(walkingSpeed, "/player/down1.png", "/player/down2.png"),
                new AnimatedSprite(walkingSpeed, "/player/left1.png", "/player/left2.png"));
        var running = new DirectedAnimatedSprite(
                new AnimatedSprite(runningSpeed, "/player/up1.png", "/player/up2.png"),
                new AnimatedSprite(runningSpeed, "/player/right1.png", "/player/right2.png"),
                new AnimatedSprite(runningSpeed, "/player/down1.png", "/player/down2.png"),
                new AnimatedSprite(runningSpeed, "/player/left1.png", "/player/left2.png"));
        return new LivingEntitySprite(idle, walking, running);
    }
}
