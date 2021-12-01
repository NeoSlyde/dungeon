package model.items;

import model.entities.LivingEntity;

public abstract class Item {
    public final String name;
    public final String imagePath;

    public Item(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public abstract void use(LivingEntity user);

    public String getImagePath() {
        return imagePath;
    }

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
