package model.entities;

import model.Room;
import model.misc.Updatable;
import model.misc.Vec2;

// An entity in the World.
//
// Its position and size are NOT in pixel, but in the game world size unit.
// (Think of it as a minecraft block: it's 1x1x1, not 32x32x32 or whatever)
//
// An entity can be bounded to a room or not.
//
// Note: I went with canBeUpdated(), canBeUsed()... methods. I tried a component 
// approach, but then it either led to downcasting or a single Entity class 
// for everything (a.k.a an ECS, but it breaks OCP)
public abstract class Entity implements Updatable {
    private Room room;
    private Vec2 position, size;

    public Entity(Room room, Vec2 position, Vec2 size) {
        this.room = room;
        this.position = position;
        this.size = size;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room.removeEntity(this);
        this.room = room;
        room.addEntity(this);
    }

    public Vec2 getPosition() {
        return position;
    }

    public void setPosition(Vec2 position) {
        this.position = position;
    }

    public Vec2 getSize() {
        return size;
    }

    public void setSize(Vec2 size) {
        this.size = size;
    }

    public boolean collidesWith(Vec2 position, Vec2 size) {
        return this.position.x < position.x + size.x && this.position.x + this.size.x > position.x
                && this.position.y < position.y + size.y && this.position.y + this.size.y > position.y;
    }
}
