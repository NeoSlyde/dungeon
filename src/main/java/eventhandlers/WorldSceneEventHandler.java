package eventhandlers;

import model.World;
import model.misc.Direction;

public class WorldSceneEventHandler implements EventHandler {
    private MovementInputConverter movementInputConverter = new MovementInputConverter();
    private World world;

    public WorldSceneEventHandler(World world) {
        this.world = world;
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
        world.getPlayer().setRunning(keyEventType.equals(KeyEventType.PRESSED));
    }

    @Override
    public void onDirection(KeyEventType keyEventType, Direction direction) {
        movementInputConverter.keyEvent(direction, keyEventType);
        world.getPlayer().setFacingDirection(movementInputConverter.getDirection());
        world.getPlayer().setMoving(movementInputConverter.isMoving());
    }

}
