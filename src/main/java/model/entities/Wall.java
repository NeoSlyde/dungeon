package model.entities;

import model.World;
import model.misc.Position;
import model.misc.Size;
import view.View;

public class Wall extends Entity{

    public Wall(Position position, Size size, View view) {
        super(position, size, view);
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
