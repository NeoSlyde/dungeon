package model;

import java.util.ArrayList;
import java.util.List;

import model.entities.Entity;

public class World {
  private List<Entity> entities = new ArrayList<>();

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
}
