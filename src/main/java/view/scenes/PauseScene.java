package view.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import javax.sound.sampled.Clip;

import eventhandlers.EventHandler;
import eventhandlers.PauseSceneEventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.items.Item;
import model.misc.Vec2;
import model.world.World;
import view.DrawableVisitor;

public class PauseScene implements Scene {
    private EventHandler evtHandler;
    private World world;
    private SceneContext ctx;
    private Clip pauseTheme;

    public PauseScene(SceneContext ctx, World world, Scene previousScene) {
        this.ctx = ctx;
        this.evtHandler = new PauseSceneEventHandler(ctx, previousScene);

        this.world = world;
        pauseTheme = ctx.getAudioPlayer().play(ctx.getAudioDataFactory().pauseMenuTheme());

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
        VBox pauseMenu = new VBox();
        pauseMenu.setPrefSize(ctx.windowSize.x, ctx.windowSize.y);
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setSpacing(10);
        pauseMenu.setFillWidth(false);

        // Button quit = new Button("LEAVE THE GAME");
        // quit.getStylesheets().addAll("/style/menu.css");

        VBox inventoryComponent = new VBox();
        inventoryComponent.setPrefSize(ctx.windowSize.x, ctx.windowSize.y);
        inventoryComponent.setAlignment(Pos.CENTER);
        inventoryComponent.setFillWidth(false);
        inventoryComponent.setSpacing(-30);

        TilePane inventory = new TilePane();
        inventory.setAlignment(Pos.CENTER);
        inventory.setHgap(10);
        inventory.setVgap(10);
        inventory.setPrefColumns(6);
        inventory.setPrefRows(2);

        // make a black rectangle
        try {
            generateSlots(inventory);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
            e1.printStackTrace();
        }

        inventoryComponent.getChildren().add(inventory);

        // pauseMenu.getChildren().add(quit);

        pauseMenu.getChildren().add(inventoryComponent);

        return pauseMenu;
    }

    @Override
    public EventHandler getEventHandler() {
        return evtHandler;
    }

    private void generateSlots(TilePane tilePane) throws FileNotFoundException {
        tilePane.getChildren().clear();
        Map<Item, Integer> items = world.getPlayer().getInventory().getItems();
        int count = 0;
        int numItems = 12;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            tilePane.getChildren().add(createSlot(entry, tilePane));
            count++;
        }
        if (count < numItems) {
            for (int i = count; i < numItems; i++) {
                tilePane.getChildren().add(createEmptySlot());
            }
        }
    }

    private Pane createEmptySlot() {
        Pane slot = new Pane();
        slot.setPrefSize(150, 150);
        slot.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-color: white; -fx-border-width: 2;");
        return slot;
    }

    private Pane createSlot(Map.Entry<Item, Integer> entry, TilePane tilePane) {
        Pane slot = new Pane();
        slot.setPrefSize(150, 150);
        slot.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-border-color: white; -fx-border-width: 2;");

        Canvas itemCanvas = new Canvas();
        Vec2 itemCanvasSize = new Vec2(150, 150);
        itemCanvas.setWidth(itemCanvasSize.x);
        itemCanvas.setHeight(itemCanvasSize.y);
        entry.getKey().draw(new DrawableVisitor(Vec2.ZERO, itemCanvasSize, itemCanvas.getGraphicsContext2D(),
                ctx.getGraphicsFactory()));
        slot.getChildren().add(itemCanvas);

        Text amount = new Text("x" + entry.getValue().toString());
        // put text in the bottom of the slot
        amount.setLayoutX(slot.getPrefWidth() / 2 - amount.getLayoutBounds().getWidth() / 2 + 50);
        amount.setLayoutY(slot.getPrefHeight() - amount.getLayoutBounds().getHeight() - 5 + 10);
        amount.setFill(Color.WHITE);

        // make amount bigger
        amount.setScaleX(1.5);
        amount.setScaleY(1.5);

        slot.getChildren().add(amount);

        slot.setOnMouseClicked(e -> {
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSFX());
            entry.getKey().use(world.getPlayer());
            world.getPlayer().getInventory().removeItem(entry.getKey(), 1);
            if (entry.getValue() == 0) {
                world.getPlayer().getInventory().removeItem(entry.getKey());
                tilePane.getChildren().remove(slot);
                tilePane.getChildren().add(createEmptySlot());
            }
            amount.setText("x" + entry.getValue().toString());
        });

        amount.setStyle("-fx-font-weight: bold;");

        return slot;
    }

    public World getWorld() {
        return world;
    }

}
