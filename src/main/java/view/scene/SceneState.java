package view.scene;

public class SceneState {
  private Scene current = null;

  public void setScene(Scene scene) {
    if (current != null)
      current.exit();
    current = scene;
    current.enter();
  }
}
