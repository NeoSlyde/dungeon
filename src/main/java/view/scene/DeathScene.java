package view.scene;

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
}
