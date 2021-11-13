package model.entities;
import java.util.Random;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.World;
import model.misc.Direction;
import model.misc.Position;
import model.misc.Size;
import view.DirectedSprite;

public class Skeleton extends Monster {

    private DirectedSprite sprite = DirectedSprite.fromImagePaths(
      new String[] { "/monster/skeleton/up0.png", "/monster/skeleton/up1.png", "/monster/skeleton/up2.png" },
      new String[] { "/monster/skeleton/down0.png", "/monster/skeleton/down1.png", "/monster/skeleton/down2.png" },
      new String[] { "/monster/skeleton/right0.png", "/monster/skeleton/right1.png", "/monster/skeleton/right2.png" },
      new String[] { "/monster/skeleton/left0.png", "/monster/skeleton/left1.png", "/monster/skeleton/left2.png" });

    public Skeleton(Position position) {
        super(position, new Size(1, 1));
    }

    public void AI(double dt, World world){
        Random r = new Random();
        int x = r.nextInt(3);
        int y = r.nextInt(3);
        if(x == 0){
            if(y == 0){
                setFacingDirection(Direction.NORTH);
                move(dt, world);
            }
            else if(y == 1){
                setFacingDirection(Direction.SOUTH);
                move(dt, world);
            }
            else{
                setFacingDirection(Direction.EAST);
                move(dt, world);
            }
        }
        else if(x == 1){
            if(y == 0){
                setFacingDirection(Direction.EAST);
                move(dt, world);
            }
            else if(y == 1){
                setFacingDirection(Direction.WEST);
                move(dt, world);
            }
            else{
                setFacingDirection(Direction.SOUTH);
                move(dt, world);
            }
        }
        else{
            if(y == 0){
                setFacingDirection(Direction.SOUTH);
                move(dt, world);
            }
            else if(y == 1){
                setFacingDirection(Direction.NORTH);
                move(dt, world);
            }
            else{
                setFacingDirection(Direction.WEST);
                move(dt, world);
            }
        }

    }

    public void move(double dt, World world) {
        var newX = getPosition().x + getFacingDirection().unitX() * dt * getSpeed();
        var newY = getPosition().y + getFacingDirection().unitY() * dt * getSpeed();
        Position newPosition = new Position(newX, newY, getPosition().room);
        if (newX + 32 < 960 && newY + 32 < 540 && newX > 0 && newY > 0) {
            setPosition(newPosition);
        }
        sprite.update(dt);
    }


    @Override
  public void update(double dt, World world) {
    sprite.setSpeed(getSpeed() * 3);
    Random move = new Random();
    if (move.nextBoolean()) {
      var newX = getPosition().x + getFacingDirection().unitX() * dt * getSpeed();
      var newY = getPosition().y + getFacingDirection().unitY() * dt * getSpeed();
      if (newX + 32 < 960 && newY + 32 < 540 && newX > 0 && newY > 0) {
        setPosition(new Position(newX, newY, getPosition().room));
      }
      sprite.update(dt);
    } else {
      sprite.setIdle();
    }
  }

  @Override
  public void draw(GraphicsContext gc, Size windowSize) {
    Image imagef = SwingFXUtils.toFXImage(sprite.getImage(getFacingDirection()), null);
    gc.drawImage(imagef, getPosition().x, getPosition().y, 32, 32);
  }

    

}
