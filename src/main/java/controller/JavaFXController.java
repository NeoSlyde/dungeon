package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.entities.Player;
import model.items.Pickaxe;
import model.misc.Direction;
import view.scene.SceneState;

public class JavaFXController {
  EventHandler<? super KeyEvent> onKeyPressed;
  EventHandler<? super KeyEvent> onKeyRelease;

  private Player player;
  private List<KeyCode> pressedKeys = new ArrayList<>();

  private void updatePlayerMoving() {
    if (pressedKeys.isEmpty()) {
      player.setMoving(false);
    } else {
      var lastPressedKey = pressedKeys.get(pressedKeys.size() - 1);
      if (lastPressedKey == KeyCode.UP) {
        player.setFacingDirection(Direction.NORTH);
      } else if (lastPressedKey == KeyCode.DOWN) {
        player.setFacingDirection(Direction.SOUTH);
      } else if (lastPressedKey == KeyCode.LEFT) {
        player.setFacingDirection(Direction.WEST);
      } else if (lastPressedKey == KeyCode.RIGHT) {
        player.setFacingDirection(Direction.EAST);
      }
      player.setMoving(true);
    }
  }

  JavaFXController(Player player, GraphicsContext gc, SceneState sceneState) {
    this.player = player;

    onKeyPressed = (event) -> {
      var keyCode = event.getCode();
      if (keyCode == KeyCode.UP || keyCode == KeyCode.DOWN || keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT) {
        if (!pressedKeys.contains(keyCode))
          pressedKeys.add(keyCode);
        updatePlayerMoving();
      } else if (keyCode == KeyCode.SHIFT) {
        player.setRunning(true);
      } else if (keyCode == KeyCode.SPACE) {
        sceneState.getScene().onUseInput();
      } else if (keyCode == KeyCode.TAB) {
        player.setInventoryOpen(true);
      }
      else if (keyCode == KeyCode.X) {
        //get pickaxe in player inventory
        Pickaxe pickaxe = player.getInventory().getPickaxe();
        if (pickaxe != null) {
          pickaxe.use(player);
        }
      }
    };

    onKeyRelease = (event) -> {
      var keyCode = event.getCode();
      if (keyCode == KeyCode.UP || keyCode == KeyCode.DOWN || keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT) {
        pressedKeys.remove(keyCode);
        updatePlayerMoving();
      } else if (keyCode == KeyCode.SHIFT) {
        player.setRunning(false);
      } else if (keyCode == KeyCode.TAB) {
        player.setInventoryOpen(false);
      }
    };
  }
}
