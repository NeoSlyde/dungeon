package model.items;

import model.entities.LivingEntity;
import view.DrawableVisitor;

public class HealingPotion extends Item {
    public HealingPotion() {
        super("Healing Potion");
    }

    @Override
    public void use(LivingEntity user) {
        user.heal(10);
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }
}
