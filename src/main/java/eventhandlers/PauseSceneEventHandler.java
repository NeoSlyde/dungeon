package eventhandlers;

import audio.AudioPlayer;
import audio.StandardAudioDataFactory;
import model.World;
import model.misc.Direction;
import view.scenes.SceneContext;
import view.scenes.WorldScene;

public class PauseSceneEventHandler implements EventHandler {
    private World world;
    AudioPlayer audioPlayer;

    public PauseSceneEventHandler(World world) {
        this.world = world;
        audioPlayer = new AudioPlayer();
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
        // TODO Auto-generated method stub

    }

    @Override
    public void onEscape(KeyEventType type, SceneContext sceneContext) {
        if (type == KeyEventType.PRESSED) {
            audioPlayer.play(new StandardAudioDataFactory().PauseCloseSoundEffect());
            sceneContext.switchScene(new WorldScene(sceneContext, world));
        }
    }

}
