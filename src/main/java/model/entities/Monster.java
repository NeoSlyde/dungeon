package model.entities;

import controller.Sound;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.World;
import model.misc.Position;
import model.misc.Room;
import model.misc.Size;
import view.DirectedSprite;
import view.Drawable;

public abstract class Monster extends LivingEntity {

  public Monster(Position position, Size size, DirectedSprite sprite) {
    super(position, size, sprite);
  }

  @Override
  public void use(LivingEntity entity) {
    Sound soundEffect = new Sound();
    System.out.println("Implement Fighting Scene pls hee hee");
    this.setHealth(this.getHealth() - 20);
    if(this.getHealth() <= 0) {
      soundEffect.setFile(3);
      soundEffect.play();
      this.setPosition(new Position(0,0,new Room(1000)));
    }
  }

  @Override
  public boolean canBeUsed() {
    return true;
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
    gc.setStroke(Color.GREEN);
    gc.strokeText(Double.toString(this.getHealth()) + " HP", Drawable.VIRTUAL_TO_PX * (getPosition().x) - 15,
        Drawable.VIRTUAL_TO_PX * (getPosition().y) - 10);
  }

}
