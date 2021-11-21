package model.items;

import model.Item;
import model.entities.LivingEntity;

public class StrengthPotion extends Item{

    private int strengthValue = 20;

    @Override
    public boolean canBeUsed() {
        return true;
    }

    @Override
    public void use(LivingEntity user) {
        user.setStrength(user.getStrength() + strengthValue);
    }
    
}
