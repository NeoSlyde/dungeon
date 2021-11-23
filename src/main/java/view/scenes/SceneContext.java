package view.scenes;

import eventhandlers.EventHandler;
import javafx.scene.Node;

public class SceneContext {
    // RAII note: we can't pass it in the constructor because of cyclic dependency
    private Scene current = null;

    public void switchScene(Scene scene) {
        if (current != null)
            current.onLeave();
        current = scene;
        current.onEnter();
    }

    // Scene must have been set to call this method
    public Node getUI() {
        return current.getUI();
    }

    // Scene must have been set to call this method
    public EventHandler getEventHandler() {
        return current.getEventHandler();
    }
}
