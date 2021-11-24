package view.scenes;

import eventhandlers.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import model.misc.Direction;

public class DummyScene implements Scene {

    @Override
    public void onEnter() {
    }

    @Override
    public void onLeave() {
    }

    @Override
    public Node getUI() {
        return new Canvas();
    }

    @Override
    public EventHandler getEventHandler() {
        return new EventHandler() {
            @Override
            public void onMenu(KeyEventType keyEventType) {

            }

            @Override
            public void onSpacebar(KeyEventType keyEventType) {

            }

            @Override
            public void onLeftShift(KeyEventType keyEventType) {

            }

            @Override
            public void onDirection(KeyEventType keyEventType, Direction direction) {

            }

        };
    }

}
