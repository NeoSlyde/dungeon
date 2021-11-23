package view.scenes;

import javafx.scene.Node;
import view.scenes.eventhandlers.EventHandler;

public interface Scene {
    void onEnter();

    void onLeave();

    Node getUI();

    EventHandler getEventHandler();
}
