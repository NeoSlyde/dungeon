package view.scene;

import javafx.scene.canvas.GraphicsContext;
import model.misc.Size;
import view.sound.Sound;

public class DeathScene implements Scene {
  private Sound bgMusic = new Sound("/sounds/death.wav", -20.0f);

  public DeathScene(SceneState state) {
  }

  @Override
  public void enter() {
    bgMusic.play();
  }

  @Override
  public void exit() {
    bgMusic.stop();
  }

  @Override
  public void update(double dt) {

  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    // TODO Auto-generated method stub

  }
}
