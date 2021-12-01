package model.attacks;

public class ThunderAttack implements Attack {
    @Override
    public double getMana() {
        return 30;
    }

    @Override
    public double getStrength() {
        return 3;
    }
}
