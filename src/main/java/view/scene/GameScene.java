package view.scene;

import view.sound.Sound;

public class GameScene implements Scene {
  private Sound bgMusic = new Sound("/sounds/music.wav", -20.0f);

  public GameScene(SceneState state) {
  }

  @Override
  public void enter() {
    bgMusic.loop();
  }

  @Override
  public void exit() {
    bgMusic.stop();
  }

}
