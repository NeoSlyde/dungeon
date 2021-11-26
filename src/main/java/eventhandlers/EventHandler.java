package eventhandlers;

import model.misc.Direction;
import view.scenes.SceneContext;

public interface EventHandler {
    default void onEscape(KeyEventType type, SceneContext sceneContext) {
    }

    default void onSpacebar(KeyEventType keyEventType) {
    }

    default void onLeftShift(KeyEventType keyEventType) {
    }

    default void onDirection(KeyEventType keyEventType, Direction direction) {
    }

    public static enum KeyEventType {
        PRESSED, RELEASED
    }
}
