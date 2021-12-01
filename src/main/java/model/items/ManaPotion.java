package model.items;

import model.entities.LivingEntity;

public class ManaPotion extends Item {
    public ManaPotion() {
        super("Mana Potion", "src/main/resources/item/potion/potion_purple_red.png");
    }

    @Override
    public void use(LivingEntity user) {
        user.setMana(user.getMana() + 10);
    }
}
