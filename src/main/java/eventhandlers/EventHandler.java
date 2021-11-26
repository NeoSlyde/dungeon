package eventhandlers;

import model.misc.Direction;
import view.scenes.SceneContext;

public interface EventHandler {
    void onEscape(KeyEventType type, SceneContext sceneContext);

    void onSpacebar(KeyEventType keyEventType);

    void onLeftShift(KeyEventType keyEventType);

    void onDirection(KeyEventType keyEventType, Direction direction);

    public static enum KeyEventType {
        PRESSED, RELEASED
    }
}
