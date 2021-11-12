package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;

public class Wall extends Entity{

    public Wall(Position position, Size size) {
        super(position, size);
    }

    @Override
    public boolean canBeUsed() {
        return false;
    }

    @Override
    public void update(double dt, World world) {
        //Do nothing
    }
    
}
