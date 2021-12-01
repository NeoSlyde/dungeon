package model.attacks;

public interface Attack {
    // Required mana to use the attack
    double getMana();

    // This factor will be multiplied with the player's stregth
    double getStrength();
}
