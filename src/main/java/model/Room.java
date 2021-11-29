package model;

import java.util.ArrayList;
import java.util.List;

import model.entities.Entity;
import model.misc.Updatable;
import model.misc.Vec2;
import model.world.World;
import view.DrawableVisitor;

public class Room implements Updatable {
    private final List<Entity> entities = new ArrayList<>();
    private World world = null;
    public static final Vec2 defaultSize = new Vec2(32, 18);
    public final Vec2 size = defaultSize;

    public Room() {

    }

    // ! Can only be called by Entity's constructor!
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    // ! Can only be called by World constructor!
    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public void update(double dt) {
        entities.forEach(e -> e.update(dt));
    }

    public void draw(DrawableVisitor d) {
        d.draw(this);
    }
}
