package view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import model.entities.Wall;

public class ResourceManager {
  public static final ResourceManager INSTANCE = new ResourceManager();

  private Map<String, WritableImage> writableImages = new HashMap<>();

  public WritableImage getWritableImage(String path) {
    return writableImages.compute(path, (k, v) -> (v == null) ? createWritableImage(path) : v);
  }

  private WritableImage createWritableImage(String path) {
    try {
      return SwingFXUtils.toFXImage(ImageIO.read(Wall.class.getResourceAsStream(path)), null);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
