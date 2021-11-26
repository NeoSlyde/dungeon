package model;

import java.util.List;

import model.entities.Player;
import model.misc.Updatable;
import view.DrawableVisitor;

public class World implements Updatable {
    private final List<Room> rooms;
    private final Player player;
    private double t = 0;

    public World(List<Room> rooms, Player player) {
        for (var room : rooms)
            room.setWorld(this);
        this.rooms = rooms;
        this.player = player;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update(double dt) {
        t += dt;
        rooms.forEach(r -> r.update(dt));
    }

    public double getT() {
        return t;
    }

    public void draw(DrawableVisitor d) {
        player.getRoom().draw(d);
    }
}
