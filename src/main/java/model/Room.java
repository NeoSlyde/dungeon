package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.entities.Entity;
import model.misc.Updatable;

public class Room implements Updatable {
    public final World world;
    private List<Entity> entities = new ArrayList<>();

    public Room(World world) {
        this.world = world;
    }

    // ! Can only be called by Entity's constructor!
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    // ! Can only be called by Entity's constructor!
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public Stream<Entity> getCloseEntities(Entity entity) {
        return entities.stream().filter(e -> e != entity);
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    @Override
    public void update(double dt) {
        entities.forEach(e -> e.update(dt));
    }
}
