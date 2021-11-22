package view.scenes;

import model.misc.Vec2;

public interface EventHandler {
    void onMenu();

    void onSpacebar();

    void onLeftShift();

    /**
     * @param direction A unit vector indicating the direction of the movement, or
     *                  (0, 0) if not moving.
     */
    void onChangeDirection(Vec2 direction);
}
