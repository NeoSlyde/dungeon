package eventhandlers;

import model.misc.Direction;
import model.world.World;
import view.scenes.PauseScene;
import view.scenes.SceneContext;

public class WorldSceneEventHandler implements EventHandler {
    private MovementInputConverter movementInputConverter = new MovementInputConverter();
    private World world;

    public WorldSceneEventHandler(World world) {
        this.world = world;
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

    @Override
    public void onEscape(KeyEventType type, SceneContext sceneContext) {
        if (type == KeyEventType.PRESSED) {
            sceneContext.getAudioPlayer().play(sceneContext.getAudioDataFactory().pauseOpenSoundEffect());
            sceneContext.switchScene(new PauseScene(sceneContext, world));
        }
    }

}
