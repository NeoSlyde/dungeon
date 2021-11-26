package eventhandlers;

import model.World;
import view.scenes.SceneContext;
import view.scenes.WorldScene;

public class PauseSceneEventHandler implements EventHandler {
    private World world;

    public PauseSceneEventHandler(World world) {
        this.world = world;
    }

    @Override
    public void onEscape(KeyEventType type, SceneContext sceneContext) {
        if (type == KeyEventType.PRESSED) {
            sceneContext.getAudioPlayer().play(sceneContext.getAudioDataFactory().pauseCloseSoundEffect());
            sceneContext.switchScene(new WorldScene(sceneContext, world));
        }
    }

}
