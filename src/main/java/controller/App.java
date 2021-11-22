package controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class App extends Application {
  private void initState() {

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    initState();
    Group root = new Group();
    int windowWidth = 0;
    int windowHeight = 0;
    Canvas canvas = new Canvas(windowWidth, windowHeight);
    root.getChildren().add(canvas);
    Scene scene = new Scene(root);

    primaryStage.setTitle("Eidoslyde's Dungeon");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }
}
