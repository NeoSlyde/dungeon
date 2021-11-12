package view;

import javafx.scene.canvas.GraphicsContext;
import model.misc.Size;

public interface Drawable {
  public static int VIRTUAL_TO_PX = 32;

  void draw(GraphicsContext gc, Size windowSize);
}
