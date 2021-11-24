package model;

import java.util.List;

import model.entities.Player;
import model.misc.Updatable;
import view.Drawable;
import view.DrawableVisitor;

public class World implements Updatable, Drawable {
    private final List<Room> rooms;
    private final Player player;

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
        rooms.forEach(r -> r.update(dt));
    }

    @Override
    public void draw(DrawableVisitor d) {
        player.getRoom().draw(d);
    }
}
