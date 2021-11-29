package model.items;

import model.entities.LivingEntity;

public abstract class Item {
    public final String name;
    public final String imagePath;
    public final int value;

    public Item(String name, String imagePath, int value) {
        this.name = name;
        this.imagePath = imagePath;
        this.value = value;
    }

    public abstract void use(LivingEntity user);

    public abstract String getImagePath();

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
