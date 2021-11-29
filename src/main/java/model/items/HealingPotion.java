package model.items;

import model.entities.LivingEntity;

public class HealingPotion extends Item {

    public HealingPotion() {
        super("Healing Potion", "src/main/resources/item/potion/ruby_new.png", 10);
    }

    @Override
    public void use(LivingEntity user) {
        user.heal(value);
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

}
