package model.entities;

import javafx.scene.canvas.GraphicsContext;
import model.Inventory;
import model.World;
import model.misc.Position;
import model.misc.Size;
import view.Drawable;
import view.ResourceManager;

public class Chest extends Entity {

    private Inventory inventory;

    public Chest(Position position) {
        super(position, new Size(1, 1));
    }

    @Override
    public boolean canBeUsed() {
        return true;
    }

    @Override
    public void use(LivingEntity entity) {
        // TODO: Open chest in UI
    }

    @Override
    public void update(double dt, World world) {
        // Do nothing
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void draw(GraphicsContext gc, Size windowSize) {
        var image = ResourceManager.INSTANCE.getWritableImage("/dungeon/chest.png");
        gc.drawImage(image, getPosition().x * Drawable.VIRTUAL_TO_PX, getPosition().y * Drawable.VIRTUAL_TO_PX,
                Drawable.VIRTUAL_TO_PX * getSize().width, Drawable.VIRTUAL_TO_PX * getSize().height);
    }

}
