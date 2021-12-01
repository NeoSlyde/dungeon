package view.scenes;

import javax.sound.sampled.Clip;

import animatefx.animation.FadeIn;
import eventhandlers.EventHandler;
import eventhandlers.WorldSceneEventHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;
import model.misc.Vec2;
import model.world.World;
import view.DrawableVisitor;

public class WorldScene implements Scene {
    private static final double dt = 1.0 / 144;

    private World world;

    private EventHandler evtHandler;

    private Canvas canvas;

    private Timeline tl;

    private Clip worldTheme;

    public WorldScene(SceneContext ctx, World world) {
        this.world = world;
        this.evtHandler = new WorldSceneEventHandler(world, ctx);
        canvas = new Canvas(ctx.windowSize.x, ctx.windowSize.y);
        worldTheme = ctx.getAudioPlayer().play(ctx.getAudioDataFactory().gameplayPeacefulTheme());
        worldTheme.stop();
        tl = new Timeline(new KeyFrame(Duration.seconds(dt), e -> {
            world.update(dt);
            if (world.getPlayer().getEnemy().isPresent()) {
                ctx.switchScene(new BattleScene(ctx, world));
            }
            if (world.getPlayer().getHasWon()) {
                ctx.switchScene(new VictoryScene(ctx));
            }
            world.draw(new DrawableVisitor(Vec2.ZERO, ctx.windowSize,
                    canvas.getGraphicsContext2D(), ctx.getGraphicsFactory()));
        }));
        tl.setCycleCount(Timeline.INDEFINITE);

    }

    @Override
    public void onEnter() {
        tl.play();
        FadeIn canvasOpen = new FadeIn(canvas);
        worldTheme.start();
        canvasOpen.play();
    }

    @Override
    public void onLeave() {
        tl.stop();
        worldTheme.stop();
    }

    @Override
    public Node getUI() {
        return canvas;
    }

    @Override
    public EventHandler getEventHandler() {
        return evtHandler;
    }

    public World getWorld() {
        return world;
    }
}
