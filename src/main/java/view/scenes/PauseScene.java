package view.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.sound.sampled.Clip;

import audio.AudioPlayer;
import audio.StandardAudioDataFactory;
import eventhandlers.EventHandler;
import eventhandlers.PauseSceneEventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.World;
import model.misc.Vec2;

public class PauseScene implements Scene {

    private EventHandler evtHandler;

    private World world;

    Clip pauseTheme;
    AudioPlayer audioPlayer;

    public PauseScene(SceneContext ctx, World world) {
        this.evtHandler = new PauseSceneEventHandler(world);

        this.world = world;

        audioPlayer = new AudioPlayer();

        pauseTheme = audioPlayer.play(new StandardAudioDataFactory().pauseMenuTheme());
    }

    @Override
    public void onEnter() {
        pauseTheme.start();
    }

    @Override
    public void onLeave() {
        pauseTheme.stop();
    }

    @Override
    public Node getUI() {
        final Vec2 windowSize = new Vec2(16 * 70, 9 * 70);
        VBox pauseMenu = new VBox();
        pauseMenu.setPrefSize(windowSize.x, windowSize.y);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setSpacing(10);

        VBox inventoryComponent = new VBox();
        inventoryComponent.setPrefSize(windowSize.x, windowSize.y);
        inventoryComponent.setAlignment(Pos.CENTER);
        inventoryComponent.setSpacing(-80);

        TilePane inventory1 = new TilePane();
        inventory1.setPrefSize(windowSize.x, windowSize.y);
        inventory1.setAlignment(Pos.CENTER);
        inventory1.setHgap(10);
        inventory1.setVgap(10);

        TilePane inventory2 = new TilePane();
        inventory2.setPrefSize(windowSize.x, windowSize.y);
        inventory2.setAlignment(Pos.CENTER);
        inventory2.setHgap(10);
        inventory2.setVgap(10);

        // make a black rectangle

        generateSlots(inventory1);
        generateSlots(inventory2);

        try {

            Image pauseImage = new Image(new FileInputStream("src/main/resources/gui/pause.png"));

            ImageView pause = new ImageView(pauseImage);

            // make pauseImage smaller
            pause.setFitWidth(pauseImage.getWidth() / 2);
            pause.setFitHeight(pauseImage.getHeight() / 2);

            pauseMenu.getChildren().add(pause);

            Image inventoryIm = new Image(new FileInputStream("src/main/resources/gui/inventory.png"));

            ImageView inventoryImage = new ImageView(inventoryIm);

            inventoryImage.setFitHeight(inventoryIm.getHeight() * 0.6);
            inventoryImage.setFitWidth(inventoryIm.getWidth() * 0.6);

            Image backgroundImage = new Image(new FileInputStream("src/main/resources/gui/inventorybggrey.png"));
            BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
            pauseMenu.setBackground(new Background(background));

            inventoryComponent.getChildren().add(inventoryImage);

        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        inventoryComponent.getChildren().add(inventory1);
        inventoryComponent.getChildren().add(inventory2);

        pauseMenu.getChildren().add(inventoryComponent);

        return pauseMenu;
    }

    @Override
    public EventHandler getEventHandler() {
        return evtHandler;
    }

    private void generateSlots(TilePane tilePane) {
        tilePane.getChildren().clear();
        for (int i = 0; i < 6; i++) {
            tilePane.getChildren().add(createSlot());
        }
    }

    private Rectangle createSlot() {
        Rectangle rectangle = new Rectangle(150, 150);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(5);
        rectangle.setFill(Color.GREY);

        rectangle.setOnMouseClicked(e -> audioPlayer.play(new StandardAudioDataFactory().selectSoundEffect()));

        return rectangle;
    }

    public World getWorld() {
        return world;
    }

}
