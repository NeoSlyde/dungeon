package eventhandlers;

import java.util.ArrayList;
import java.util.List;

import model.misc.Direction;

// Converts direction key inputs into movement state.
public class MovementInputConverter {
    private Direction direction = Direction.RIGHT;
    private List<Direction> keyPressStack = new ArrayList<>();

    public void keyEvent(Direction direction, EventHandler.KeyEventType keyEventType) {
        if (keyEventType == EventHandler.KeyEventType.PRESSED && !keyPressStack.contains(direction))
            keyPressStack.add(direction);
        else if (keyEventType == EventHandler.KeyEventType.RELEASED)
            keyPressStack.remove(direction);
        if (!keyPressStack.isEmpty())
            this.direction = keyPressStack.get(keyPressStack.size() - 1);
    }

    public boolean isMoving() {
        return !keyPressStack.isEmpty();
    }

    public Direction getDirection() {
        return direction;
    }
}
