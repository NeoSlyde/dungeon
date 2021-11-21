package model.items;

import model.Item;
import model.entities.LivingEntity;

public class BigHealingPotion extends Item{

    private int healingValue = 60;

    @Override
    public boolean canBeUsed() {
        return true;
    }

    @Override
    public void use(LivingEntity user) {
        user.setHealth(user.getHealth() + healingValue);
    }
    
}
