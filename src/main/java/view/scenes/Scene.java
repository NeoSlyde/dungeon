package view.scenes;

import eventhandlers.EventHandler;
import javafx.scene.Node;

public interface Scene {
    void onEnter();

    void onLeave();

    Node getUI();

    EventHandler getEventHandler();
}
