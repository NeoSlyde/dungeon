package view.sprites;

import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import view.DrawableVisitor;

public class Sprite {
    public final Image image;

    public Sprite(String path) {
        this.image = createWritableImage(path);
    }

    public void draw(DrawableVisitor d) {
        d.draw(this);
    }

    private WritableImage createWritableImage(String path) {
        try {
            return SwingFXUtils.toFXImage(ImageIO.read(getClass().getResourceAsStream(path)), null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
