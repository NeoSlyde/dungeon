package model.entities;

import javafx.scene.canvas.GraphicsContext;
import model.Inventory;
import model.World;
import model.misc.Position;
import model.misc.Size;
import view.Drawable;
import view.ResourceManager;
import view.sound.Sound;

public class Chest extends Entity {

    private Inventory inventory;

    public Chest(Position position, Inventory inventory, World world) {
        super(position, new Size(1, 1), world);
        this.inventory = inventory;
    }

    @Override
    public boolean canBeUsed() {
        return true;
    }

    @Override
    public void use(LivingEntity entity) {
        entity.getInventory().addAll(this.inventory);
        new Sound("/sounds/open.wav", 0f).play();
        this.setPosition(new Position(1000, 1000, this.getPosition().room));

        inventory.getItems().keySet().forEach(item -> {
            System.out.println("You got: " + item.getName());
        });

        
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
