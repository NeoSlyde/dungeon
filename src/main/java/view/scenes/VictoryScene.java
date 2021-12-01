package view.scenes;

import eventhandlers.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import model.misc.Vec2;
import view.DrawableVisitor;

public class VictoryScene implements Scene {
    private SceneContext ctx;

    public VictoryScene(SceneContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onEnter() {
        ctx.getAudioPlayer().play(ctx.getAudioDataFactory().deathSFX());
        new Thread(() -> {
            try {
                Thread.sleep(2500);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public Node getUI() {
        var canvas = new Canvas();
        canvas.setWidth(ctx.windowSize.x);
        canvas.setHeight(ctx.windowSize.y);
        ctx.getGraphicsFactory().victory()
                .draw(new DrawableVisitor(Vec2.ZERO, ctx.windowSize,
                        canvas.getGraphicsContext2D(), ctx.getGraphicsFactory()));
        return canvas;
    }

    @Override
    public EventHandler getEventHandler() {
        return new EventHandler() {
        };
    }
}
