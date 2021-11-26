package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.entities.Entity;
import model.misc.Updatable;
import model.misc.Vec2;
import view.DrawableVisitor;

public class Room implements Updatable {
    private final List<Entity> entities = new ArrayList<>();
    private World world = null;
    public final Vec2 size = new Vec2(32, 18);

    public Room() {

    }

    // ! Can only be called by Entity's constructor!
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    // ! Can only be called by Entity's constructor!
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

    public Stream<Entity> getCloseEntities(Entity entity) {
        return entities.stream().filter(e -> e != entity);
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
