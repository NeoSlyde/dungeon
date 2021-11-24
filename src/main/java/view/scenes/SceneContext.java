package view.scenes;

import eventhandlers.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;

public class SceneContext {
    private Scene current = new DummyScene();
    public final Group root;

    public SceneContext(Group root) {
        this.root = root;
    }

    public void switchScene(Scene scene) {
        root.getChildren().remove(current.getUI());
        current.onLeave();

        current = scene;

        current.onEnter();
        root.getChildren().add(current.getUI());
    }

    public Node getUI() {
        return current.getUI();
    }

    public EventHandler getEventHandler() {
        return current.getEventHandler();
    }
}
