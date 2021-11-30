package eventhandlers;

import model.misc.Direction;

public interface EventHandler {
    default void onEscape(KeyEventType type) {
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
