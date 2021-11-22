package view.scenes;

import javafx.scene.Node;

public interface Scene {
    Node getUI();

    EventHandler getEventHandler();
}
