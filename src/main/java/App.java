
import java.util.Random;

import audio.AudioPlayer;
import audio.StandardAudioDataFactory;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.entities.monsters.RandomMonsterFactory;
import model.world.RandomWorldFactory;
import view.scenes.MainMenuScene;
import view.scenes.SceneContext;
import view.sprites.StandardGraphicsFactory;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);

        var audioDataFactory = new StandardAudioDataFactory();
        var audioPlayer = new AudioPlayer();
        var graphicsFactory = new StandardGraphicsFactory();
        var sceneCtx = new SceneContext(root, audioDataFactory, graphicsFactory, audioPlayer);

        var random = new Random();
        sceneCtx.switchScene(new MainMenuScene(sceneCtx,
                new RandomWorldFactory(random, new RandomMonsterFactory(random), audioPlayer, audioDataFactory)));
        var controller = new JavaFXController(sceneCtx);

        scene.setOnKeyPressed(controller::handleKeyPressed);
        scene.setOnKeyReleased(controller::handleKeyReleased);
        primaryStage.setTitle("EidoSlyde Dungeon");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
