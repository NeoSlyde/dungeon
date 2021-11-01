package model;

import model.entities.LivingEntity;

public interface Usable {
  boolean canBeUsed();

  default void use(LivingEntity user) {

  }
}
