package model;

import java.util.ArrayList;
import java.util.List;

import model.entities.Entity;
import model.misc.Position;

public class World {
  private List<Entity> entities = new ArrayList<>();
  private Position spawnPoint;

  public World() {

  }

  public World(Position spawnPoint) {
    this.spawnPoint = spawnPoint;
  }

  public void addEntity(Entity entity) {
    entities.add(entity);
  }

  public void removeEntity(Entity entity) {
    entities.remove(entity);
  }

  public List<Entity> getEntities() {
    return entities;
  }

  public void update(double dt) {
    for (Entity entity : entities) {
      entity.update(dt, this);
    }
  }

  public Position getSpawnPoint() {
    return spawnPoint;
  }
}
