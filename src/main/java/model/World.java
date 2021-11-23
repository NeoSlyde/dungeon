package model;

import java.util.List;

import model.misc.Updatable;

public class World implements Updatable {
    private List<Room> rooms;

    public World(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public void update(double dt) {
        rooms.forEach(r -> r.update(dt));
    }
}
