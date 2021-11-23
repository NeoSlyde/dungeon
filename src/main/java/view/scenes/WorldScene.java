package view.scenes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.util.Duration;
import model.World;
import model.misc.Vec2;
import view.scenes.eventhandlers.EventHandler;
import view.scenes.eventhandlers.WorldSceneEventHandler;

public class WorldScene implements Scene {
    private static final double dt = 0.016;

    private World world;

    private Canvas canvas;

    private Timeline tl;

    public WorldScene(World world) {
        this.world = world;

        final Vec2 windowSize = new Vec2(800, 600);
        canvas = new Canvas(windowSize.x, windowSize.y);
        tl = new Timeline(new KeyFrame(Duration.seconds(dt), e -> {
            world.update(dt);
        }));
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
        return new WorldSceneEventHandler(world);
    }
}
