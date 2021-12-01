package eventhandlers;

import model.world.World;
import view.scenes.BattleScene;
import view.scenes.PauseScene;
import view.scenes.SceneContext;

public class BattleSceneEventHandler implements EventHandler {
    private World world;
    private SceneContext sceneContext;

    public BattleSceneEventHandler(World world, SceneContext sceneContext) {
        this.world = world;
        this.sceneContext = sceneContext;
    }

    @Override
    public void onEscape(KeyEventType type) {
        if (type == KeyEventType.PRESSED) {
            sceneContext.getAudioPlayer().play(sceneContext.getAudioDataFactory().pauseOpenSFX());
            sceneContext.switchScene(new PauseScene(sceneContext, world, new BattleScene(sceneContext, world)));
        }
    }

}
