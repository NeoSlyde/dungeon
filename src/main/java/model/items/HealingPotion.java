package model.items;

import model.Item;
import model.entities.LivingEntity;

public class HealingPotion extends Item{

    private int healingValue = 30;

    public HealingPotion() {
        super("Healing Potion");
    }

    @Override
    public boolean canBeUsed() {
        return true;
    }

    @Override
    public void use(LivingEntity user) {
        user.setHealth(user.getHealth() + healingValue);
    }
    
}
