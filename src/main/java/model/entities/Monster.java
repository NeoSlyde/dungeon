package model.entities;

import java.util.Random;

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
  
  Sound defeated = new Sound();

  public Monster(Position position, Size size, DirectedSprite sprite) {
    super(position, size, sprite);
    defeated.setFile(3);
  }

  @Override
  public void use(LivingEntity entity) {
    Sound hit = new Sound();
    Random damage = new Random();
    entity.setInCombat(true);

    new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
              hit.setFile(5);
              hit.play();
              setHealth(getHealth() - damage.nextInt(30));
            }
        }, 
        100
    );
    
    new java.util.Timer().schedule( 
        new java.util.TimerTask() {
            @Override
            public void run() {
              hit.setFile(5);
              hit.play();
              entity.setHealth(entity.getHealth() - damage.nextInt(10));
            }
        }, 
        300
    );
    
  }

  @Override
  public boolean canBeUsed() {
    return true;
  }

  @Override
  public void update(double dt, World world) {
    super.update(dt, world);
    if(this.getHealth() <= 0) {
      defeated.play();
      this.setPosition(new Position(0,0,new Room(1000)));
      world.getPlayer().setInCombat(false);
    }
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
