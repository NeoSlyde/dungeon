package eventhandlers;

import model.entities.Entity;
import model.entities.monsters.Monster;
import model.misc.Direction;
import model.world.World;
import view.scenes.PauseScene;
import view.scenes.SceneContext;
import view.scenes.WorldScene;

public class WorldSceneEventHandler implements EventHandler {
    private MovementInputConverter movementInputConverter = new MovementInputConverter();
    private World world;
    private SceneContext sceneContext;

    public WorldSceneEventHandler(World world, SceneContext sceneContext) {
        this.world = world;
        this.sceneContext = sceneContext;
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
    public void onEscape(KeyEventType type) {
        if (type == KeyEventType.PRESSED) {
            sceneContext.getAudioPlayer().play(sceneContext.getAudioDataFactory().pauseOpenSFX());
            sceneContext.switchScene(new PauseScene(sceneContext, world, new WorldScene(sceneContext, world)));
        }
    }

    @Override
    public void onSpacebar(KeyEventType type) {
        world.getPlayer().getFacingEntities()
                .filter(Entity::isUsable)
                .findAny()
                .ifPresent(e -> e.use(world.getPlayer()));

        world.getPlayer().getFacingEntities()
                .filter(e -> e instanceof Monster)
                .map(e -> (Monster) e)
                .findAny()
                .ifPresent(world.getPlayer()::setEnemy);
    }
}
