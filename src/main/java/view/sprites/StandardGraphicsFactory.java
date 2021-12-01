package view.sprites;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class StandardGraphicsFactory implements GraphicsFactory {
    public LivingEntitySprite player() {
        return cachedPlayerSprite;
    }

    @Override
    public LivingEntitySprite skeleton() {
        return cachedMonsterSpriteSkeleton;
    }

    @Override
    public LivingEntitySprite goblin() {
        return cachedMonsterSpriteGoblin;
    }

    @Override
    public LivingEntitySprite ghost() {
        return cachedMonsterSpriteGhost;
    }

    @Override
    public Sprite wall() {
        return cachedWallSprite;
    }

    @Override
    public Sprite door() {
        return cachedDoorSprite;
    }

    @Override
    public ImagePattern floorPattern() {
        return cachedFloorPattern;
    }

    @Override
    public Sprite gameOver() {
        return cachedGameOver;
    }

    private LivingEntitySprite cachedPlayerSprite = loadSpriteUtil("/player/");
    private LivingEntitySprite cachedMonsterSpriteSkeleton = loadSpriteUtil("/monster/skeleton/");
    private LivingEntitySprite cachedMonsterSpriteGoblin = loadSpriteUtil("/monster/goblin/");
    private LivingEntitySprite cachedMonsterSpriteGhost = loadSpriteUtil("/monster/ghost/");
    private Sprite cachedWallSprite = new Sprite("/dungeon/wall/stone_gray_0.png");
    private Sprite cachedDoorSprite = new Sprite("/dungeon/doors/runed_door.png");
    private ImagePattern cachedFloorPattern = new ImagePattern(new Image("/dungeon/floor/grey_dirt_0_old.png"), 0, 0,
            32, 32, false);
    private Sprite cachedGameOver = new Sprite("/gui/gameover.jpg");

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
