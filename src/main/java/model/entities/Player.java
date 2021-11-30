package model.entities;

import java.util.Optional;

import model.Room;
import model.entities.monsters.Monster;
import model.misc.Vec2;
import view.DrawableVisitor;

public class Player extends LivingEntity {
    public Player(Room room, Vec2 position) {
        super(room, position, new Vec2(0.5, 0.5));
    }

    private Optional<Monster> enemy = Optional.empty();

    @Override
    public double getStrength() {
        return 20;
    }

    @Override
    public double getSpeed() {
        return isRunning() ? 8 : 4;
    }

    @Override
    public void draw(DrawableVisitor d) {
        d.draw(this);
    }

    public Optional<Monster> getEnemy() {
        return enemy;
    }

    public void setEnemy(Monster enemy) {
        this.enemy = Optional.of(enemy);
    }

    public void clearEnemy() {
        this.enemy = Optional.empty();
    }

    @Override
    public String getName() {
        return "You";
    }
}
