package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.entities.Player;
import model.misc.Direction;

public class JavaFXController {
  EventHandler<? super KeyEvent> onKeyPressed;
  EventHandler<? super KeyEvent> onKeyRelease;

  private Player player;
  private List<KeyCode> pressedKeys = new ArrayList<>();

  private void updatePlayerMoving() {
    if(!player.isStopped()){
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
  }

  JavaFXController(Player player, GraphicsContext gc) {
    this.player = player;

    onKeyPressed = (event) -> {
      switch (event.getCode()) {
      case UP:
        if (!pressedKeys.contains(KeyCode.UP))
          pressedKeys.add(KeyCode.UP);
        updatePlayerMoving();
        break;
      case DOWN:
        if (!pressedKeys.contains(KeyCode.DOWN))
          pressedKeys.add(KeyCode.DOWN);
        updatePlayerMoving();
        break;
      case LEFT:
        if (!pressedKeys.contains(KeyCode.LEFT))
          pressedKeys.add(KeyCode.LEFT);
        updatePlayerMoving();
        break;
      case RIGHT:
        if (!pressedKeys.contains(KeyCode.RIGHT))
          pressedKeys.add(KeyCode.RIGHT);
        updatePlayerMoving();
        break;
      case SHIFT:
        player.setRunning(true);
        break;
      case SPACE:
          player.useFacing();
        break;
        case TAB:
          player.openInventory();
        break;  
      default:
        break;
      }
    };

    onKeyRelease = (event) -> {
      switch (event.getCode()) {
      case UP:
        pressedKeys.remove(KeyCode.UP);
        updatePlayerMoving();
        break;
      case DOWN:
        pressedKeys.remove(KeyCode.DOWN);
        updatePlayerMoving();
        break;
      case LEFT:
        pressedKeys.remove(KeyCode.LEFT);
        updatePlayerMoving();
        break;
      case RIGHT:
        pressedKeys.remove(KeyCode.RIGHT);
        updatePlayerMoving();
        break;
      case SHIFT:
        player.setRunning(false);
        break;
        case TAB:
          player.closeInventory();
        break; 
      default:
        break;
      }
    };
  }

  public void mouseClicked(MouseEvent e) {
    if(e.getSceneX() < 469 && e.getSceneX() > 416 && e.getSceneY() < 262 && e.getSceneY() > 208){
      System.out.println("inventory slot 1");
    }
    if(e.getSceneX() < 540 && e.getSceneX() > 484 && e.getSceneY() < 262 && e.getSceneY() > 208){
      System.out.println("inventory slot 2");
    }
    if(e.getSceneX() < 611 && e.getSceneX() > 555 && e.getSceneY() < 262 && e.getSceneY() > 208){
      System.out.println("inventory slot 3");
    }
    if(e.getSceneX() < 469 && e.getSceneX() > 416 && e.getSceneY() < 332 && e.getSceneY() > 277){
      System.out.println("inventory slot 4");
    }
    if(e.getSceneX() < 540 && e.getSceneX() > 484 && e.getSceneY() < 332 && e.getSceneY() > 277){
      System.out.println("inventory slot 5");
    }
    if(e.getSceneX() < 611 && e.getSceneX() > 555 && e.getSceneY() < 332 && e.getSceneY() > 277){
      System.out.println("inventory slot 6");
    }
    if(e.getSceneX() < 469 && e.getSceneX() > 416 && e.getSceneY() < 402 && e.getSceneY() > 347){
      System.out.println("inventory slot 7");
    }
    if(e.getSceneX() < 540 && e.getSceneX() > 484 && e.getSceneY() < 402 && e.getSceneY() > 347){
      System.out.println("inventory slot 8");
    }
    if(e.getSceneX() < 611 && e.getSceneX() > 555 && e.getSceneY() < 402 && e.getSceneY() > 347){
      System.out.println("inventory slot 9");
    }



  }
}
