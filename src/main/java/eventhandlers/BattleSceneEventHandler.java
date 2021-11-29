package eventhandlers;

import model.world.World;
import view.scenes.SceneContext;
import view.scenes.WorldScene;

public class BattleSceneEventHandler implements EventHandler {
    private World world;

    public BattleSceneEventHandler(World world) {
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
