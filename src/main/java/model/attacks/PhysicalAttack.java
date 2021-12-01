package model.attacks;

public class PhysicalAttack implements Attack {
    @Override
    public double getMana() {
        return 0;
    }

    @Override
    public double getStrength() {
        return 1;
    }
}
