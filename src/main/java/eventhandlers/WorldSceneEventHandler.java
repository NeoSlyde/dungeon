package eventhandlers;

import audio.AudioPlayer;
import audio.StandardAudioDataFactory;
import model.World;
import model.misc.Direction;
import view.scenes.PauseScene;
import view.scenes.SceneContext;

public class WorldSceneEventHandler implements EventHandler {
    private MovementInputConverter movementInputConverter = new MovementInputConverter();
    private World world;
    AudioPlayer audioPlayer;

    public WorldSceneEventHandler(World world) {
        this.world = world;
        audioPlayer = new AudioPlayer();
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

    @Override
    public void onEscape(KeyEventType type, SceneContext sceneContext) {
        if (type == KeyEventType.PRESSED) {
            audioPlayer.play(new StandardAudioDataFactory().pauseOpenSoundEffect());
            sceneContext.switchScene(new PauseScene(sceneContext, world));
        }
    }

}
