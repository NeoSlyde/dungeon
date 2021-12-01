package eventhandlers;

import view.scenes.Scene;
import view.scenes.SceneContext;

public class PauseSceneEventHandler implements EventHandler {
    private SceneContext sceneContext;
    private Scene previousScene;

    public PauseSceneEventHandler(SceneContext sceneContext, Scene previousScene) {
        this.sceneContext = sceneContext;
        this.previousScene = previousScene;
    }

    @Override
    public void onEscape(KeyEventType type) {
        if (type == KeyEventType.PRESSED) {
            sceneContext.getAudioPlayer().play(sceneContext.getAudioDataFactory().pauseCloseSFX());
            sceneContext.switchScene(previousScene);
        }
    }

}
