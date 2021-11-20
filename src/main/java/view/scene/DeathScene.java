package view.scene;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.misc.Size;
import view.ResourceManager;
import view.sound.Sound;

public class DeathScene implements Scene {
  private Sound bgMusic = new Sound("/sounds/death.wav", -20.0f);

  public DeathScene(SceneState state) {
  }

  @Override
  public void enter() {
    bgMusic.play();
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        System.exit(0);
      }
    }, 2500);
  }

  @Override
  public void exit() {
    bgMusic.stop();
  }

  @Override
  public void update(double dt) {
  }

  Image img = ResourceManager.INSTANCE.getWritableImage("/gui/gameover.jpg");

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    gc.drawImage(img, 0, 0, windowSize.width, windowSize.height);
  }

  @Override
  public void onUseInput() {
  }
}
