package model;

import java.util.List;

public class World {
    private List<Room> rooms;

    public World(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
