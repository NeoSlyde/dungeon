package eventhandlers;

import model.World;
import model.misc.Direction;

public class WorldSceneEventHandler implements EventHandler {
    private MovementInputConverter movementInputConverter = new MovementInputConverter();

    public WorldSceneEventHandler(World world) {

    }

    @Override
    public void onMenu(KeyEventType keyEventType) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSpacebar(KeyEventType keyEventType) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onLeftShift(KeyEventType keyEventType) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onDirection(KeyEventType keyEventType, Direction direction) {
        movementInputConverter.keyEvent(direction, keyEventType);
        // TODO Auto-generated method stub
    }

}
