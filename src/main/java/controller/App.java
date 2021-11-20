package controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.World;
import model.entities.Player;
import model.generator.RandomWorldGeneratorMk2;
import model.generator.WorldGenerator;
import model.misc.Direction;
import model.misc.Size;
import view.scene.GameScene;
import view.scene.SceneState;

public class App extends Application {
  WorldGenerator worldGenerator = new RandomWorldGeneratorMk2();

  private World world = worldGenerator.generate();
  private Player player = new Player(world.getSpawnPoint());
  private SceneState sceneState = new SceneState();

  private void initState() {
    sceneState.setScene(new GameScene(sceneState, world, player));
    player.setFacingDirection(Direction.EAST);
    world.addEntity(player);
  }

  /**
   * The main entry point for all JavaFX applications. The start method is called
   * after the init method has returned, and after the system is ready for the
   * application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param primaryStage the primary stage for this application, onto which the
   *                     application scene can be set. Applications may create
   *                     other stages, if needed, but they will not be primary
   *                     stages.
   * @throws Exception if something goes wrong
   */
  @Override
  public void start(Stage primaryStage) throws Exception {
    initState();

    Group root = new Group();
    // JavaFXView view = new JavaFXView();
    int windowWidth = 1024;
    int windowHeight = 576;
    Canvas canvas = new Canvas(windowWidth, windowHeight);
    root.getChildren().add(canvas);
    Scene scene = new Scene(root);

    GraphicsContext gc = canvas.getGraphicsContext2D();
    JavaFXController javaFXController = new JavaFXController(player, gc);
    scene.setOnKeyPressed(javaFXController.onKeyPressed);
    scene.setOnKeyReleased(javaFXController.onKeyRelease);
    double dt = 16;
    Timeline tl = new Timeline(new KeyFrame(Duration.millis(dt), e -> {
      update(dt, gc, new Size(windowWidth, windowHeight));
    }));
    tl.setCycleCount(Timeline.INDEFINITE);

    primaryStage.setTitle("Eidoslyde's Dungeon");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();

    tl.play();
  }

  private void update(double dt, GraphicsContext gc, Size windowSize) {
    sceneState.update(dt);
    sceneState.draw(gc, windowSize);
  }
}
