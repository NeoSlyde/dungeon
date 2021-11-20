package view.scene;

import java.util.Optional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.World;
import model.entities.LivingEntity;
import model.entities.Player;
import model.misc.Size;
import view.sound.Sound;

public class CombatScene implements Scene {
  protected Sound bgMusic = new Sound("/sounds/fight.wav", -20.0f);

  private World world;
  private SceneState state;
  private Player player;

  private LivingEntity currentTurn;
  private LivingEntity opponent;
  private double oppPrevHealth;

  public CombatScene(SceneState state, World world, Player player, LivingEntity firstTurn) {
    this.state = state;
    this.world = world;
    this.player = player;

    this.currentTurn = firstTurn;
    this.opponent = currentTurn.getCombatting().get();
    this.oppPrevHealth = opponent.getHealth();
  }

  public void switchTurn() {
    new Sound("/sounds/hit.wav", -10.0f).play();
    opponent = currentTurn;
    oppPrevHealth = opponent.getHealth();
    currentTurn = currentTurn.getCombatting().orElse(null);
    currentTurn.scheduleAttackBack();
  }

  @Override
  public void enter() {
    bgMusic.loop();
  }

  @Override
  public void exit() {
    bgMusic.stop();
  }

  @Override
  public void update(double dt) {
    if (oppPrevHealth != opponent.getHealth()) {
      switchTurn();
    }
    currentTurn.update(dt, world);

    if (player.getHealth() <= 0) {
      state.setScene(new DeathScene(state));
    }
    if (player.getCombatting().get().getHealth() <= 0) {
      world.removeEntity(player.getCombatting().get());
      player.setInCombat(Optional.empty());
      state.setScene(new GameScene(state, world, player));
    }
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    gc.setFill(new ImagePattern(new Image("\\dungeon\\floor\\grey_dirt_0_old.png"), 0, 0, 32, 32, false));
    gc.fillRect(0, 0, windowSize.width, windowSize.height);
    for (var entity : world.getEntities()) {
      if (entity.getPosition().room.equals(player.getPosition().room)) {
        entity.draw(gc, windowSize);
      }
    }
  }

  @Override
  public void onUseInput() {
    if (player != currentTurn)
      return;
    player.attack();
  }
}
