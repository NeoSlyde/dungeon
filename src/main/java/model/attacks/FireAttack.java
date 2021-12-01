package model.attacks;

public class FireAttack implements Attack {
    @Override
    public double getMana() {
        return 20;
    }

    @Override
    public double getStrength() {
        return 2;
    }
}
