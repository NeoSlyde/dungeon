package view.scenes.ui;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class AnimatedButton extends Button {

    public AnimatedButton(String text, Timeline timeline) {
        this.setText(text);
        this.setFont(new Font("Tahoma", 24));
    }

}
