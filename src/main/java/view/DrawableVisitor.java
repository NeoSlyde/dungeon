package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Room;
import model.entities.Entity;
import model.misc.Vec2;

public class DrawableVisitor {
    public final Vec2 posPx, sizePx;
    public final GraphicsContext ctx;

    public DrawableVisitor(Vec2 posPx, Vec2 sizePx, GraphicsContext ctx) {
        this.posPx = posPx;
        this.sizePx = sizePx;
        this.ctx = ctx;
    }

    public void draw(Room room) {
        ctx.setFill(new Color(0, 0, 0, 1));
        ctx.fillRect(posPx.x, posPx.y, sizePx.x, sizePx.y);

    }

    public void draw(Entity e) {
        ctx.setFill(new Color(1, 1, 1, 1));
        ctx.fillRect(posPx.x, posPx.y, sizePx.x, sizePx.y);
    }
}
