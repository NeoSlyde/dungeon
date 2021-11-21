package model.entities;

import javafx.scene.canvas.GraphicsContext;
import model.World;
import model.misc.Position;
import model.misc.Size;
import view.Drawable;
import view.ResourceManager;

public class Wall extends Entity {
    public Wall(Position position, World world) {
        super(position, new Size(1, 1), world);
    }

    @Override
    public boolean canBeUsed() {
        return false;
    }

    @Override
    public void update(double dt, World world) {
        // Do nothing
    }

    @Override
    public void draw(GraphicsContext gc, Size windowSize) {
        var image = ResourceManager.INSTANCE.getWritableImage("/dungeon/wall/stone_gray_0.png");
        gc.drawImage(image, getPosition().x * Drawable.VIRTUAL_TO_PX, getPosition().y * Drawable.VIRTUAL_TO_PX,
                Drawable.VIRTUAL_TO_PX * getSize().width, Drawable.VIRTUAL_TO_PX * getSize().height);
    }
}
