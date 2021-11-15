package model;

import java.util.ArrayList;
import java.util.List;

import controller.Sound;
import model.entities.Entity;
import model.entities.Player;
import model.misc.Position;

public class World {
  private List<Entity> entities = new ArrayList<>();
  public Sound worldMusic = new Sound();

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
    Player player = (Player) this.getEntities().stream().filter(e -> e instanceof Player).findFirst().get();
    for (Entity entity : entities) {
      entity.update(dt, this);
    }
    if(player.getHealth() <= 0) {
      worldMusic.stop();
    }

  }

  public Position getSpawnPoint() {
    return spawnPoint;
  }

  public Player getPlayer() {
    return (Player) this.getEntities().stream().filter(e -> e instanceof Player).findFirst().get();
  }
}
