package view.scene;

import javafx.scene.canvas.GraphicsContext;
import model.misc.Size;
import view.Drawable;

public class SceneState implements Drawable {
  private Scene current = null;

  public Scene getScene() {
    return current;
  }

  public void setScene(Scene scene) {
    if (current != null)
      current.exit();
    current = scene;
    current.enter();
  }

  public void update(double dt) {
    if (current != null)
      current.update(dt);
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    if (current != null)
      current.draw(gc, windowSize);
  }
}
