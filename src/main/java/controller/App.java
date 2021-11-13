package controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import model.World;
import model.entities.Player;
import model.entities.Skeleton;
import model.generator.RandomWorldGenerator;
import model.generator.WorldGenerator;
import model.misc.Position;
import model.misc.Room;
import model.misc.Size;
import model.misc.Direction;

public class App extends Application {
  WorldGenerator worldGenerator = new RandomWorldGenerator();

  private World world = worldGenerator.generate();
  private Player player = new Player(new Position(1, 1, new Room(0)));
  private Skeleton skeleton = new Skeleton(new Position(5, 5, new Room(0)));

  private Sound soundManager = new Sound();

  private void initState() {
    player.setFacingDirection(Direction.EAST);
    skeleton.setFacingDirection(Direction.WEST);
    world.addEntity(player);
    world.addEntity(skeleton);

    soundManager.setFile(0);
    // soundManager.play();
    // soundManager.loop();

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
    int windowWidth = 960;
    int windowHeight = 540;
    Canvas canvas = new Canvas(windowWidth, windowHeight);
    root.getChildren().add(canvas);
    Scene scene = new Scene(root);

    JavaFXController javaFXController = new JavaFXController(player);
    scene.setOnKeyPressed(javaFXController.onKeyPressed);
    scene.setOnKeyReleased(javaFXController.onKeyRelease);

    GraphicsContext gc = canvas.getGraphicsContext2D();
    double dt = 16;
    Timeline tl = new Timeline(new KeyFrame(Duration.millis(dt), e -> {
      update(dt, gc, new Size(windowWidth, windowHeight));
    }));
    tl.setCycleCount(Timeline.INDEFINITE);

    canvas.setOnMouseClicked(e -> {
      // javaFXController.mouseClicked(e);
    });

    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();

    tl.play();
  }

  private void update(double dt, GraphicsContext gc, Size windowSize) {
    gc.setFill(new ImagePattern(new Image("\\dungeon\\floor\\lair_2_new.png"), 0, 0, 32, 32, false));
    gc.fillRect(0, 0, windowSize.width, windowSize.height);

    for (var entity : world.getEntities()) {
      entity.update(dt, world);
    }
    for (var entity : world.getEntities()) {
      entity.draw(gc, windowSize);
    }
  }
}
