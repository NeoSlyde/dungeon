package view.scenes;

import eventhandlers.EventHandler;
import javafx.scene.Node;

public interface Scene {
    default void onEnter() {
    }

    default void onLeave() {
    }

    Node getUI();

    EventHandler getEventHandler();
}
