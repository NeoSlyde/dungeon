package model.entities;

import model.Room;
import model.misc.Vec2;

public interface EntityFactory {
    Entity generate(Room room, Vec2 position);
}
