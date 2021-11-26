
import audio.StandardAudioDataFactory;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Room;
import model.entities.Player;
import model.misc.Vec2;
import view.scenes.MainMenuScene;
import view.scenes.SceneContext;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);

        var audioDataFactory = new StandardAudioDataFactory();

        var sceneCtx = new SceneContext(root, audioDataFactory);
        var room = new Room();
        var player = new Player(room, new Vec2(1, 1));
        room.addEntity(player);
        sceneCtx.switchScene(new MainMenuScene(sceneCtx));
        var controller = new JavaFXController(sceneCtx);

        scene.setOnKeyPressed(controller::handleKeyPressed);
        scene.setOnKeyReleased(controller::handleKeyReleased);
        primaryStage.setTitle("EidoSlyde Dungeon");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
