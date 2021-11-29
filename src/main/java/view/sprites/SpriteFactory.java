package view.sprites;

public class SpriteFactory {
    public static final SpriteFactory INSTANCE = new SpriteFactory();

    public LivingEntitySprite playerSprite() {
        return cachedPlayerSprite;
    }

    public LivingEntitySprite skeletonSprite() {
        return cachedMonsterSpriteSkeleton;
    }

    public LivingEntitySprite goblinSprite() {
        return cachedMonsterSpriteGoblin;
    }

    public LivingEntitySprite ghostSprite() {
        return cachedMonsterSpriteGhost;
    }

    public Sprite wallSprite() {
        return cachedWallSprite;
    }

    private LivingEntitySprite cachedPlayerSprite = loadSpriteUtil("/player/");
    private LivingEntitySprite cachedMonsterSpriteSkeleton = loadSpriteUtil("/monster/skeleton/");
    private LivingEntitySprite cachedMonsterSpriteGoblin = loadSpriteUtil("/monster/goblin/");
    private LivingEntitySprite cachedMonsterSpriteGhost = loadSpriteUtil("/monster/ghost/");
    private Sprite cachedWallSprite = new Sprite("/dungeon/wall/stone_gray_0.png");

    private LivingEntitySprite loadSpriteUtil(String path) {
        var walkingSpeed = 4;
        var runningSpeed = 8;
        var idle = new DirectedAnimatedSprite(
                new AnimatedSprite(0, path + "up0.png"),
                new AnimatedSprite(0, path + "right0.png"),
                new AnimatedSprite(0, path + "down0.png"),
                new AnimatedSprite(0, path + "left0.png"));
        var walking = new DirectedAnimatedSprite(
                new AnimatedSprite(walkingSpeed, path + "up1.png", path + "up2.png"),
                new AnimatedSprite(walkingSpeed, path + "right1.png", path + "right2.png"),
                new AnimatedSprite(walkingSpeed, path + "down1.png", path + "down2.png"),
                new AnimatedSprite(walkingSpeed, path + "left1.png", path + "left2.png"));
        var running = new DirectedAnimatedSprite(
                new AnimatedSprite(runningSpeed, path + "up1.png", path + "up2.png"),
                new AnimatedSprite(runningSpeed, path + "right1.png", path + "right2.png"),
                new AnimatedSprite(runningSpeed, path + "down1.png", path + "down2.png"),
                new AnimatedSprite(runningSpeed, path + "left1.png", path + "left2.png"));
        return new LivingEntitySprite(idle, walking, running);
    }
}