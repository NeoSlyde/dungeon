package view.scenes;

import audio.AudioDataFactory;
import audio.AudioPlayer;
import eventhandlers.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import model.misc.Vec2;
import view.sprites.GraphicsFactory;

public class SceneContext {
    private Scene current = new DummyScene();
    private AudioDataFactory audioDataFactory;
    private GraphicsFactory graphicsFactory;
    private AudioPlayer audioPlayer;
    private final Group root;
    public final Vec2 windowSize = new Vec2(16 * 70, 9 * 70);

    public SceneContext(Group root, AudioDataFactory audioDataFactory, GraphicsFactory graphicsFactory,
            AudioPlayer audioPlayer) {
        this.root = root;
        this.audioDataFactory = audioDataFactory;
        this.graphicsFactory = graphicsFactory;
        this.audioPlayer = audioPlayer;
    }

    public void switchScene(Scene scene) {
        root.getChildren().remove(current.getUI());
        current.onLeave();

        current = scene;

        current.onEnter();
        root.getChildren().add(current.getUI());
        root.requestFocus();
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

    public GraphicsFactory getGraphicsFactory() {
        return graphicsFactory;
    }
}
