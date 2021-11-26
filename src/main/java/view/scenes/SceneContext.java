package view.scenes;

import audio.AudioDataFactory;
import audio.AudioPlayer;
import eventhandlers.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import model.misc.Vec2;

public class SceneContext {
    private Scene current = new DummyScene();
    private AudioDataFactory audioDataFactory;
    private AudioPlayer audioPlayer = new AudioPlayer();
    private final Group root;
    public final Vec2 windowSize = new Vec2(16 * 70, 9 * 70);

    public SceneContext(Group root, AudioDataFactory audioDataFactory) {
        this.root = root;
        this.audioDataFactory = audioDataFactory;
    }

    public void switchScene(Scene scene) {
        root.getChildren().remove(current.getUI());
        current.onLeave();

        current = scene;

        current.onEnter();
        root.getChildren().add(current.getUI());
        current.getUI().requestFocus();
    }

    public Node getUI() {
        return current.getUI();
    }

    public EventHandler getEventHandler() {
        return current.getEventHandler();
    }

    public AudioDataFactory getAudioDataFactory() {
        return audioDataFactory;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
