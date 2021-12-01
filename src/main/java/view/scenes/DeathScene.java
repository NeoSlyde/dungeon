package view.scenes;

import eventhandlers.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import model.misc.Vec2;
import view.DrawableVisitor;

public class DeathScene implements Scene {
    private SceneContext ctx;

    public DeathScene(SceneContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void onEnter() {
        ctx.getAudioPlayer().play(ctx.getAudioDataFactory().deathSFX());
        new Thread(() -> {
            try {
                Thread.sleep(600);
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
        ctx.getSpriteFactory().gameOverImg()
                .draw(new DrawableVisitor(Vec2.ZERO, ctx.windowSize,
                        canvas.getGraphicsContext2D(), ctx.getSpriteFactory()));
        return canvas;
    }

    @Override
    public EventHandler getEventHandler() {
        return new EventHandler() {
        };
    }
}
