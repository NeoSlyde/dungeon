package model.generator;

import java.util.Random;

import model.Inventory;
import model.items.HealingPotion;
import model.items.Pickaxe;
import model.items.StrengthPotion;

public class RandomInventoryGenerator {
    public Inventory generate() {
        Random r = new Random();
        int objectId = r.nextInt(3);
        Inventory inventory = new Inventory();
        if(objectId == 0) {
            inventory.add(new Pickaxe());
        }
        else if (objectId == 1) {
            inventory.add(new HealingPotion());
        }
        else if (objectId == 2) {
            inventory.add(new StrengthPotion());
        }
        return inventory;
    }
}
