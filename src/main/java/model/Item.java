package model;

import model.entities.LivingEntity;

public abstract class Item {
    public abstract void use(LivingEntity user);
}
