package model.entities;

import javafx.scene.canvas.GraphicsContext;
import model.World;
import model.misc.Position;
import model.misc.Size;
import view.Drawable;
import view.ResourceManager;

public class Door extends Entity {
    Position destination;

    public Door(Position position, Position destination) {
        super(position, new Size(1,1));
        this.destination = destination;
    }

    @Override
    public boolean canBeUsed() {
        return true;
    }

    @Override
    public void use(LivingEntity entity) {
        entity.setPosition(destination);
    }

    @Override
    public void update(double dt, World world) {

    }

    @Override
    public void draw(GraphicsContext gc, Size windowSize) {
        var image = ResourceManager.INSTANCE.getWritableImage("/dungeon/doors/runed_door.png");
        gc.drawImage(image, getPosition().x * Drawable.VIRTUAL_TO_PX, getPosition().y * Drawable.VIRTUAL_TO_PX,
                Drawable.VIRTUAL_TO_PX * getSize().width, Drawable.VIRTUAL_TO_PX * getSize().height);
    }
}
