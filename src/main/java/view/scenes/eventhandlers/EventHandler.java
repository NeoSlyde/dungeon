package view.scenes.eventhandlers;

import model.misc.Direction;

public interface EventHandler {
    void onMenu(KeyEventType keyEventType);

    void onSpacebar(KeyEventType keyEventType);

    void onLeftShift(KeyEventType keyEventType);

    void onDirection(KeyEventType keyEventType, Direction direction);

    public static enum KeyEventType {
        PRESSED, RELEASED
    }
}
