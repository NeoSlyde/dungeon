import eventhandlers.EventHandler.KeyEventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.misc.Direction;
import view.scenes.SceneContext;

public class JavaFXController {
    private SceneContext sceneContext;

    public JavaFXController(SceneContext sceneContext) {
        this.sceneContext = sceneContext;
    }

    public void handleKeyPressed(KeyEvent keyEvent) {
        handleKey(KeyEventType.PRESSED, keyEvent);
    }

    public void handleKeyReleased(KeyEvent keyEvent) {
        handleKey(KeyEventType.RELEASED, keyEvent);
    }

    private void handleKey(KeyEventType type, KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT)
            sceneContext.getEventHandler().onDirection(type, Direction.RIGHT);
        else if (keyEvent.getCode() == KeyCode.LEFT)
            sceneContext.getEventHandler().onDirection(type, Direction.LEFT);
        else if (keyEvent.getCode() == KeyCode.UP)
            sceneContext.getEventHandler().onDirection(type, Direction.UP);
        else if (keyEvent.getCode() == KeyCode.DOWN)
            sceneContext.getEventHandler().onDirection(type, Direction.DOWN);
        else if (keyEvent.getCode() == KeyCode.SPACE)
            sceneContext.getEventHandler().onSpacebar(type);
        else if (keyEvent.getCode() == KeyCode.ESCAPE)
            sceneContext.getEventHandler().onMenu(type);
        else if (keyEvent.getCode() == KeyCode.SHIFT)
            sceneContext.getEventHandler().onLeftShift(type);
    }
}
