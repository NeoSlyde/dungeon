package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Room;
import model.entities.Chest;
import model.entities.Door;
import model.entities.Entity;
import model.entities.Player;
import model.entities.Wall;
import model.entities.monsters.Ghost;
import model.entities.monsters.Goblin;
import model.entities.monsters.Skeleton;
import model.misc.Vec2;
import view.sprites.Sprite;
import view.sprites.GraphicsFactory;

public class DrawableVisitor {
    public final Vec2 posPx, sizePx;
    public final GraphicsContext ctx;
    public final GraphicsFactory graphicsFactory;

    public DrawableVisitor(Vec2 posPx, Vec2 sizePx, GraphicsContext ctx, GraphicsFactory graphicsFactory) {
        this.posPx = posPx;
        this.sizePx = sizePx;
        this.ctx = ctx;
        this.graphicsFactory = graphicsFactory;
    }

    public void draw(Sprite sprite) {
        ctx.drawImage(sprite.image, posPx.x, posPx.y, sizePx.x, sizePx.y);
    }

    public void draw(Room room) {
        ctx.setFill(graphicsFactory.floorPattern());
        ctx.fillRect(posPx.x, posPx.y, sizePx.x, sizePx.y);

        var gameSizeFactor = sizePx.divide(room.size);

        for (var e : room.getEntities()) {
            var d = new DrawableVisitor(posPx.add(e.getPosition().multiply(gameSizeFactor)),
                    e.getSize().multiply(gameSizeFactor), ctx, graphicsFactory);
            e.draw(d);
        }
    }

    public void draw(Entity e) {
        ctx.setFill(new Color(1, 1, 1, 1));
        ctx.fillRect(posPx.x, posPx.y, sizePx.x, sizePx.y);
    }

    public void draw(Player p) {
        graphicsFactory.player().draw(scaledUp(2), p);
    }

    public void draw(Skeleton s) {
        graphicsFactory.skeleton().draw(scaledUp(2), s);
    }

    public void draw(Goblin go) {
        graphicsFactory.goblin().draw(scaledUp(2), go);
    }

    public void draw(Ghost gh) {
        graphicsFactory.ghost().draw(scaledUp(2), gh);
    }

    public void draw(Wall w) {
        graphicsFactory.wall().draw(this);
    }

    public void draw(Door d) {
        graphicsFactory.door().draw(this);
    }

    public void draw(Chest c) {
        graphicsFactory.chest().draw(this);
    }

    public DrawableVisitor scaledUp(double factor) {
        Vec2 newSize = sizePx.multiply(factor);
        Vec2 offset = newSize.subtract(sizePx).multiply(0.5);
        return new DrawableVisitor(posPx.subtract(offset), newSize, ctx, graphicsFactory);
    }
}
