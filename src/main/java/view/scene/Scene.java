package view.scene;

import view.Drawable;

public interface Scene extends Drawable {
  void enter();

  void exit();

  void update(double dt);

  void onUseInput();
}
