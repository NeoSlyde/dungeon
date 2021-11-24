
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Room;
import model.World;
import model.entities.Player;
import model.misc.Vec2;
import view.scenes.SceneContext;
import view.scenes.WorldScene;

public class App extends Application {
    private void initState() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initState();
        Group root = new Group();
        Scene scene = new Scene(root);

        var sceneCtx = new SceneContext(root);
        var room = new Room();
        sceneCtx.switchScene(new WorldScene(sceneCtx, new World(List.of(room), new Player(room, new Vec2(1, 1)))));
        var controller = new JavaFXController(sceneCtx);

        scene.setOnKeyPressed(controller::handleKeyPressed);
        scene.setOnKeyReleased(controller::handleKeyReleased);
        primaryStage.setTitle("Eidoslyde's Dungeon");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
