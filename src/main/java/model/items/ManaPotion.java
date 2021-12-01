package model.items;

import model.entities.LivingEntity;
import view.DrawableVisitor;

public class ManaPotion extends Item {
    public ManaPotion() {
        super("Mana Potion");
    }

    @Override
    public void use(LivingEntity user) {
        user.setMana(user.getMana() + 10);
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }
}
