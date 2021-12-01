package model.items;

import model.entities.LivingEntity;
import view.DrawableVisitor;

public abstract class Item {
    public final String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract void use(LivingEntity user);

    public abstract void draw(DrawableVisitor d);

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item) {
            return ((Item) obj).name.equals(this.name);
        }
        return false;
    }
}
