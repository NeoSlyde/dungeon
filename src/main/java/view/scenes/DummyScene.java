package view.scenes;

import eventhandlers.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;

public class DummyScene implements Scene {
    @Override
    public Node getUI() {
        return new Canvas();
    }

    @Override
    public EventHandler getEventHandler() {
        return new EventHandler() {
        };
    }

}
