package model.items;

import model.Item;
import model.entities.LivingEntity;
import model.entities.Wall;
import model.misc.Position;
import model.misc.Room;
import model.misc.Size;
import view.sound.Sound;

public class Pickaxe extends Item{

    private int uses = 10;
    private static String name = "Pickaxe";

    public Pickaxe() {
        super(name);
    }

    @Override
    public boolean canBeUsed() {
        return true;
    }

    @Override
     public void use(LivingEntity user) {
         if(user.getInventory().getItems().containsKey(this)) {
            if(uses <= 0) {
                System.out.println("Your pickaxe broke, you need a new one!");
            }

            else{
             var facingPos = user.getPosition().addX(user.getFacingDirection().unitX()).addY(user.getFacingDirection().unitY());

             user.getWorld().getEntities().stream().filter(e -> e.collidesWith(facingPos, new Size(0,0))).findAny().ifPresent(e -> {
                    if(e instanceof Wall) {
                        uses--;
                        System.out.println("You used the pickaxe, "+ uses + " uses remaining");
                        new Sound("/sounds/break.wav", 0f).play();
                        e.setPosition(new Position(1000,1000,new Room(1000)));
                    }
             });

            }


             if(uses == 0) {
                 user.getInventory().remove(this);
             }
         }
    }
    
}
