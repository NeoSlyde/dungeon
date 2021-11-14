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
import model.generator.RandomWorldGeneratorMk2;
import model.generator.WorldGenerator;
import model.misc.Direction;
import model.misc.Size;
import view.ResourceManager;

public class App extends Application {
  WorldGenerator worldGenerator = new RandomWorldGeneratorMk2();

  private World world = worldGenerator.generate();
  private Player player = new Player(world.getSpawnPoint());

  private Sound soundManager = new Sound();

  private void initState() {
    player.setFacingDirection(Direction.EAST);
    world.addEntity(player);

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
    int windowWidth = 1024;
    int windowHeight = 576;
    Canvas canvas = new Canvas(windowWidth, windowHeight);
    root.getChildren().add(canvas);
    Scene scene = new Scene(root);
    Player player = (Player) world.getEntities().stream().filter(e -> e instanceof Player).findFirst().get();

    GraphicsContext gc = canvas.getGraphicsContext2D();
    JavaFXController javaFXController = new JavaFXController(player, gc);
    scene.setOnKeyPressed(javaFXController.onKeyPressed);
    scene.setOnKeyReleased(javaFXController.onKeyRelease);
    double dt = 16;
    Timeline tl = new Timeline(new KeyFrame(Duration.millis(dt), e -> {
      update(dt, gc, new Size(windowWidth, windowHeight));
    }));
    tl.setCycleCount(Timeline.INDEFINITE);

    canvas.setOnMouseClicked(e -> {
      // javaFXController.mouseClicked(e);
    });

    primaryStage.setTitle("Eidoslyde's Dungeon");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();

    tl.play();
  }

  private void update(double dt, GraphicsContext gc, Size windowSize) {
    gc.setFill(new ImagePattern(new Image("\\dungeon\\floor\\grey_dirt_0_old.png"), 0, 0, 32, 32, false));
    gc.fillRect(0, 0, windowSize.width, windowSize.height);
    Player player = (Player) world.getEntities().stream().filter(e -> e instanceof Player).findFirst().get();
    for (var entity : world.getEntities()) {
      entity.update(dt, world);
    }
    for (var entity : world.getEntities()) {
      if (entity.getPosition().room.equals(player.getPosition().room)) {
        entity.draw(gc, windowSize);
      }
    }
    Image inventoryImage = ResourceManager.INSTANCE.getWritableImage("/gui/inventory.png");
    if (player.isInventoryOpen()) {
      world.getEntities().forEach(e -> e.setStopped(true));
      gc.drawImage(inventoryImage, windowSize.width / 2 - 300 / 2, windowSize.height / 2 - 300 / 2, 300, 300);
    } else {
      world.getEntities().forEach(e -> e.setStopped(false));
    }
  }
}
