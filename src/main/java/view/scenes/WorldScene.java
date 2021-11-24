package view.scenes;

import eventhandlers.EventHandler;
import eventhandlers.WorldSceneEventHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;
import model.World;
import model.misc.Vec2;
import view.DrawableVisitor;

public class WorldScene implements Scene {
    private static final double dt = 0.016;

    private World world;

    private EventHandler evtHandler;

    private Canvas canvas;

    private Timeline tl;

    public WorldScene(SceneContext ctx, World world) {
        this.world = world;
        this.evtHandler = new WorldSceneEventHandler(world);

        final Vec2 windowSize = new Vec2(16 * 70, 9 * 70);
        canvas = new Canvas(windowSize.x, windowSize.y);
        tl = new Timeline(new KeyFrame(Duration.seconds(dt), e -> {
            world.update(dt);
            world.draw(new DrawableVisitor(Vec2.ZERO, windowSize, canvas.getGraphicsContext2D()));
        }));
        tl.setCycleCount(Timeline.INDEFINITE);

    }

    @Override
    public void onEnter() {
        tl.play();
    }

    @Override
    public void onLeave() {
        tl.stop();
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
