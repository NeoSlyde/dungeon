package model.entities;

import javafx.scene.canvas.GraphicsContext;
import model.World;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;
import view.Drawable;

public abstract class Monster extends LivingEntity {

  public Monster(Position position, Size size, DirectedSprite sprite) {
    super(position, size, sprite);
  }

  @Override
  public void update(double dt, World world) {
    super.update(dt, world);
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    gc.drawImage(sprite.getImage(getFacingDirection()), Drawable.VIRTUAL_TO_PX * (getPosition().x - 0.25),
        Drawable.VIRTUAL_TO_PX * (getPosition().y - 0.25), Drawable.VIRTUAL_TO_PX * (getSize().width + 0.5),
        Drawable.VIRTUAL_TO_PX * (getSize().height + 0.5));
  }

}
