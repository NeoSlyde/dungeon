package eventhandlers;

import model.world.World;
import view.scenes.SceneContext;
import view.scenes.WorldScene;

public class PauseSceneEventHandler implements EventHandler {
    private World world;
    private SceneContext sceneContext;

    public PauseSceneEventHandler(World world, SceneContext sceneContext) {
        this.world = world;
        this.sceneContext = sceneContext;
    }

    @Override
    public void onEscape(KeyEventType type) {
        if (type == KeyEventType.PRESSED) {
            sceneContext.getAudioPlayer().play(sceneContext.getAudioDataFactory().pauseCloseSoundEffect());
            sceneContext.switchScene(new WorldScene(sceneContext, world));
        }
    }

}
