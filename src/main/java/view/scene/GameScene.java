package view.scene;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.World;
import model.entities.Player;
import model.misc.Size;
import view.sound.Sound;

public class GameScene implements Scene {
  private Sound bgMusic = new Sound("/sounds/music.wav", -20.0f);

  private World world;
  private Player player;

  public GameScene(SceneState state, World world, Player player) {
    this.world = world;
    this.player = player;
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
    for (var entity : world.getEntities()) {
      entity.update(dt, world);
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

}
