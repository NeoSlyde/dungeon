package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Player;

public class JavaFXController {
  EventHandler<? super KeyEvent> eventHandler;

  JavaFXController(Player player) {
    eventHandler = new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
          break;
        case DOWN:
          break;
        case LEFT:
          break;
        case RIGHT:
          break;
        default:
          break;
        }
      }
    };
  }
}
